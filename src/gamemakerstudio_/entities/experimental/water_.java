package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.misc.*;
import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.util.Random;

public class water_ extends gameobject_ {
    final static int N = 128;
    final static int iter = 16;
    final static int SCALE = 4;
    float t = 0;
    Random r = new Random();
    MathUtil.ImprovedNoise noise = new MathUtil.ImprovedNoise();
    fluid_ fluid;
    public water_(float x, float y, ID id) {
        super(x, y, id);
        fluid = new fluid_((float)0.2, 0, (float)0.0000001);
    }

    @Override
    public void tick() {
        /*int cx = (int)(0.5 * game_.WIDTH/SCALE);
        int cy = (int)(0.5 * game_.HEIGHT/SCALE);*/
        int cx = 64, cy = 64;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                fluid.addDensity(cx+i, cy+j, /*random(50, 150)*/ (float)r.nextInt(100)+50);
            }
        }
        for (int i = 0; i < 2; i++) {
            float angle = (float)noise.noise(t, 0, 0) * (2*(float)Math.PI) * 2;
            PVector v = PVector.fromAngle(angle);
            v.mult((float)0.2);
            t += 0.01;
            fluid.addVelocity(cx, cy, v.x, v.y);
        }
        fluid.step();
    }

    @Override
    public void render(Graphics g) {
        fluid.renderD(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public class fluid_ {
        int size;
        float dt;
        float diff;
        float visc;

        float[] s;
        float[] density;

        float[] Vx;
        float[] Vy;

        float[] Vx0;
        float[] Vy0;

        // pass the vars cuz i'm lazy
        int N = water_.N;
        int SCALE = water_.SCALE;
        int iter = water_.iter;

        fluid_(float dt, float diffusion, float viscosity) {
            this.size = N;
            this.dt = dt;
            this.diff = diffusion;
            this.visc = viscosity;

            this.s = new float[N*N];
            this.density = new float[N*N];

            this.Vx = new float[N*N];
            this.Vy = new float[N*N];

            this.Vx0 = new float[N*N];
            this.Vy0 = new float[N*N];
        }
        void step() {
            int N          = this.size;
            float visc     = this.visc;
            float diff     = this.diff;
            float dt       = this.dt;
            float[] Vx      = this.Vx;
            float[] Vy      = this.Vy;
            float[] Vx0     = this.Vx0;
            float[] Vy0     = this.Vy0;
            float[] s       = this.s;
            float[] density = this.density;

            diffuse(1, Vx0, Vx, visc, dt);
            diffuse(2, Vy0, Vy, visc, dt);

            project(Vx0, Vy0, Vx, Vy);

            advect(1, Vx, Vx0, Vx0, Vy0, dt);
            advect(2, Vy, Vy0, Vx0, Vy0, dt);

            project(Vx, Vy, Vx0, Vy0);

            diffuse(0, s, density, diff, dt);
            advect(0, density, s, Vx, Vy, dt);
        }
        void addDensity(int x, int y, float amount) {
            int index = IX(x, y);
            this.density[index] += amount;
        }

        void addVelocity(int x, int y, float amountX, float amountY) {
            int index = IX(x, y);
            this.Vx[index] += amountX;
            this.Vy[index] += amountY;
        }

        void renderD(Graphics g) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    float x = i * SCALE;
                    float y = j * SCALE;
                    float d = this.density[IX(i, j)];
                    g.setColor(new Color(
                            MathUtil.clamp((int)d, 0, 255),
                            MathUtil.clamp((int)(d + 50) % 255, 0, 255),
                            0
                    ));
//                    g.setColor(Color.getHSBColor(((d + 50f) % 255f)/255f, 200/255, d));
//                    System.out.println(d);
//                    g.setColor(new Color((int)(d + 50) % 255,200, game_.clamp((int)d, 0, 255)));
//                noStroke();
                    g.fillRect((int)x, (int)y, SCALE, SCALE);
                }
            }
        }

        void renderV(Graphics g) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    float x = i * SCALE;
                    float y = j * SCALE;
                    float vx = this.Vx[IX(i, j)];
                    float vy = this.Vy[IX(i, j)];
//                stroke(255);

                    if (!(Math.abs(vx) < 0.1 && Math.abs(vy) <= 0.1)) {
                        g.drawLine((int)x, (int)y, (int) (x+vx*SCALE), (int)(y+vy*SCALE));
                    }
                }
            }
        }

        void fadeD() {
            for (int i = 0; i < this.density.length; i++) {
                float d = density[i];
                density[i] = (float) constrainDouble(d-0.02, 0, 255);
            }
        }
        void diffuse (int b, float[] x, float[] x0, float diff, float dt) {
            float a = dt * diff * (N - 2) * (N - 2);
            lin_solve(b, x, x0, a, 1 + 4 * a);
        }

        void lin_solve(int b, float[] x, float[] x0, float a, float c) {
            float cRecip = (float)1.0 / c;
            for (int k = 0; k < iter; k++) {
                for (int j = 1; j < N - 1; j++) {
                    for (int i = 1; i < N - 1; i++) {
                        x[IX(i, j)] =
                                (x0[IX(i, j)]
                                        + a*(    x[IX(i+1, j)]
                                        +x[IX(i-1, j)]
                                        +x[IX(i, j+1)]
                                        +x[IX(i, j-1)]
                                )) * cRecip;
                    }
                }

                set_bnd(b, x);
            }
        }
        void project(float[] velocX, float[] velocY, float[] p, float[] div) {
            for (int j = 1; j < N - 1; j++) {
                for (int i = 1; i < N - 1; i++) {
                    div[IX(i, j)] = -0.5f*(
                            velocX[IX(i+1, j)]
                                    -velocX[IX(i-1, j)]
                                    +velocY[IX(i, j+1)]
                                    -velocY[IX(i, j-1)]
                    )/N;
                    p[IX(i, j)] = 0;
                }
            }

            set_bnd(0, div);
            set_bnd(0, p);
            lin_solve(0, p, div, 1, 4);

            for (int j = 1; j < N - 1; j++) {
                for (int i = 1; i < N - 1; i++) {
                    velocX[IX(i, j)] -= 0.5f * (  p[IX(i+1, j)]
                            -p[IX(i-1, j)]) * N;
                    velocY[IX(i, j)] -= 0.5f * (  p[IX(i, j+1)]
                            -p[IX(i, j-1)]) * N;
                }
            }
            set_bnd(1, velocX);
            set_bnd(2, velocY);
        }


        void advect(int b, float[] d, float[] d0, float[] velocX, float[] velocY, float dt) {
            float i0, i1, j0, j1;

            float dtx = dt * (N - 2);
            float dty = dt * (N - 2);

            float s0, s1, t0, t1;
            float tmp1, tmp2, x, y;

            float Nfloat = N;
            float ifloat, jfloat;
            int i, j;

            for (j = 1, jfloat = 1; j < N - 1; j++, jfloat++) {
                for (i = 1, ifloat = 1; i < N - 1; i++, ifloat++) {
                    tmp1 = dtx * velocX[IX(i, j)];
                    tmp2 = dty * velocY[IX(i, j)];
                    x    = ifloat - tmp1;
                    y    = jfloat - tmp2;

                    if (x < 0.5f) x = 0.5f;
                    if (x > Nfloat + 0.5f) x = Nfloat + 0.5f;
                    i0 = (float)Math.floor(x);
                    i1 = i0 + 1.0f;
                    if (y < 0.5f) y = 0.5f;
                    if (y > Nfloat + 0.5f) y = Nfloat + 0.5f;
                    j0 = (float)Math.floor(y);
                    j1 = j0 + 1.0f;

                    s1 = x - i0;
                    s0 = 1.0f - s1;
                    t1 = y - j0;
                    t0 = 1.0f - t1;

                    int i0i = (int)i0;
                    int i1i = (int)i1;
                    int j0i = (int)j0;
                    int j1i = (int)j1;

                    // DOUBLE CHECK THIS!!!
                    d[IX(i, j)] =
                            s0 * (t0 * d0[IX(i0i, j0i)] + t1 * d0[IX(i0i, j1i)]) +
                                    s1 * (t0 * d0[IX(i1i, j0i)] + t1 * d0[IX(i1i, j1i)]);
                }
            }

            set_bnd(b, d);
        }



        void set_bnd(int b, float[] x) {
            for (int i = 1; i < N - 1; i++) {
                x[IX(i, 0  )] = b == 2 ? -x[IX(i, 1  )] : x[IX(i, 1 )];
                x[IX(i, N-1)] = b == 2 ? -x[IX(i, N-2)] : x[IX(i, N-2)];
            }
            for (int j = 1; j < N - 1; j++) {
                x[IX(0, j)] = b == 1 ? -x[IX(1, j)] : x[IX(1, j)];
                x[IX(N-1, j)] = b == 1 ? -x[IX(N-2, j)] : x[IX(N-2, j)];
            }

            x[IX(0, 0)] = 0.5f * (x[IX(1, 0)] + x[IX(0, 1)]);
            x[IX(0, N-1)] = 0.5f * (x[IX(1, N-1)] + x[IX(0, N-2)]);
            x[IX(N-1, 0)] = 0.5f * (x[IX(N-2, 0)] + x[IX(N-1, 1)]);
            x[IX(N-1, N-1)] = 0.5f * (x[IX(N-2, N-1)] + x[IX(N-1, N-2)]);
        }
        int IX(int x, int y) {
            x = constrain(x, 0, N-1);
            y = constrain(y, 0, N-1);
            return x + (y * N);
        }
        int constrain(int var, int min, int max){
            return MathUtil.clamp(var, min, max);
        }
        double constrainDouble(double var, double min, double max){
            return MathUtil.clampDouble(var, min, max);
        }
    }

}
