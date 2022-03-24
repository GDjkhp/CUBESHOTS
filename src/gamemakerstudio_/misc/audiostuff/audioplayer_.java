/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemakerstudio_.misc.audiostuff;

import com.xuggle.xuggler.ICodec;
import gamemakerstudio_.entities.experimental.osc_;
import gamemakerstudio_.game_;
import gamemakerstudio_.misc.STATE;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.StreamPlayer;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.StreamPlayerException;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.Bead;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.data.Pitch;
import net.beadsproject.beads.events.KillTrigger;
import net.beadsproject.beads.ugens.*;
import org.lwjgl.Sys;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static gamemakerstudio_.game_.*;

/**
 *
 * @author ACER
 */
public class audioplayer_ implements Runnable {
    
    public static boolean betaAudioEngine = false; // when modifying this option, change getMusic()

    public static String currentMusic = "";
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
    // beta audio engine
    /*public static StreamPlayer streamPlayer = new StreamPlayer();
    public static Map<String, MusicTest> musicTestMap = new HashMap<>();*/

    // record
    public static String record;

    public static void load(String key) {
        try {
            // default music
            musicMap.put("null", new Music("resources_/sounds_/null.ogg"));
            musicMap.put("game_over", new Music("resources_/sounds_/game_over.ogg"));
            musicMap.put("music", new Music("resources_/music_/97945.ogg")); // TODO: change to randomly generated music
            musicMap.put("shop_music", new Music("resources_/music_/reminds me of elevators.ogg"));
            // default sound
            soundMap.put("null", new Sound("resources_/sounds_/null.ogg"));
            soundMap.put("click_sound", new Sound("resources_/sounds_/Gun-Reload-Sound-Effect.ogg"));
            soundMap.put("tick", new Sound("resources_/sounds_/tick.ogg"));
            soundMap.put("first_tick", new Sound("resources_/sounds_/tickstart.ogg"));
            soundMap.put("win_1", new Sound("resources_/sounds_/win_1.ogg"));
            soundMap.put("win_2", new Sound("resources_/sounds_/win_2.ogg"));
            soundMap.put("death", new Sound("resources_/sounds_/death.ogg"));
            soundMap.put("alert", new Sound("resources_/sounds_/alert.ogg"));
            soundMap.put("laser", new Sound("resources_/sounds_/ChipShop_GB_FX_14.ogg"));
            soundMap.put("boom", new Sound("resources_/sounds_/ChipShop_YM_Noise_04.ogg"));
            // new code for custom music
            if (!betaAudioEngine) {
                switch (key) {
                    case "record":
                        musicMap.put(key, new Music("C:/Users/ACER/Desktop/stuff goes here/" + record + ".ogg", true));
                        break;
                    case "dead_meme":
                        musicMap.put(key, new Music("resources_/music_/dead meme.ogg", true));
                        break;
                    case "dead_meme_2":
                        musicMap.put(key, new Music("resources_/music_/dead meme 2.ogg", true));
                        break;
                    case "eschew":
                        musicMap.put(key, new Music("resources_/music_/eschew.ogg", true));
                        break;
                    case "rock_the_house":
                        musicMap.put(key, new Music("resources_/music_/rock the house.ogg", true));
                        break;
                    case "end_of_time":
                        musicMap.put(key, new Music("resources_/music_/end of time.ogg", true));
                        break;
                    case "nova_music":
                        musicMap.put(key, new Music("resources_/music_/nova music.ogg", true));
                        break;
                    case "time_leaper":
                        musicMap.put(key, new Music("resources_/music_/time leaper.ogg", true));
                        break;
                    case "gg":
                        musicMap.put(key, new Music("resources_/music_/gg.ogg", true));
                        break;
                    case "namice1":
                        musicMap.put(key, new Music("resources_/music_/namice1.ogg", true));
                        break;
                    case "namice2":
                        musicMap.put(key, new Music("resources_/music_/namice2.ogg", true));
                        break;
                    case "namice3":
                        musicMap.put(key, new Music("resources_/music_/namice3.ogg", true));
                        break;
                    case "there":
                        musicMap.put(key, new Music("resources_/music_/there.ogg", true));
                        break;
                    case "fisher_price":
                        musicMap.put(key, new Music("resources_/music_/fisher price.ogg", true));
                        break;
                    case "dance_violins":
                        musicMap.put(key, new Music("resources_/music_/dance violins.ogg", true));
                        break;
                    // new
                    case "aether":
                        musicMap.put(key, new Music("resources_/music_/aether.ogg", true));
                        break;
                    case "clickbait":
                        musicMap.put(key, new Music("resources_/music_/clickbait.ogg", true));
                        break;
                    case "debug":
                        musicMap.put(key, new Music("resources_/music_/debug.ogg", true));
                        break;
                    case "iy":
                        musicMap.put(key, new Music("resources_/music_/iy.ogg", true));
                        break;
                    case "ye":
                        musicMap.put(key, new Music("resources_/music_/ye.ogg", true));
                        break;
                    case "mocha":
                        musicMap.put(key, new Music("resources_/music_/mocha.ogg", true));
                        break;
                    case "everything_falls":
                        musicMap.put(key, new Music("resources_/music_/everything falls.ogg", true));
                        break;
                    case "lonely_forest":
                        musicMap.put(key, new Music("resources_/music_/lonely forest.ogg", true));
                        break;
                    case "saxophone":
                        musicMap.put(key, new Music("resources_/music_/saxophone.ogg", true));
                        break;
                    case "overcharge":
                        musicMap.put(key, new Music("resources_/music_/overcharge.ogg", true));
                        break;
                    case "flavored_ice":
                        musicMap.put(key, new Music("resources_/music_/flavored ice.ogg", true));
                        break;
                    case "shape_of_the_sun":
                        musicMap.put(key, new Music("resources_/music_/shape of the sun.ogg", true));
                        break;
                    case "angels":
                        musicMap.put(key, new Music("resources_/music_/angels.ogg", true));
                        break;
                    case "perseverance":
                        musicMap.put(key, new Music("resources_/music_/perseverance.ogg", true));
                        break;
                    case "dynasty":
                        musicMap.put(key, new Music("resources_/music_/dynasty.ogg", true));
                        break;
                    case "bloom":
                        musicMap.put(key, new Music("resources_/music_/bloom.ogg", true));
                        break;
                    case "canyon":
                        musicMap.put(key, new Music("resources_/music_/canyon.ogg", true));
                        break;
                    case "overcloud":
                        musicMap.put(key, new Music("resources_/music_/overcloud.ogg", true));
                        break;
                    case "ppaper_pplanes":
                        musicMap.put(key, new Music("resources_/music_/ppaper pplanes.ogg", true));
                        break;
                    case "prelude":
                        musicMap.put(key, new Music("resources_/music_/prelude.ogg", true));
                        break;
                    case "spirit":
                        musicMap.put(key, new Music("resources_/music_/spirit.ogg", true));
                        break;
                    case "catalyze":
                        musicMap.put(key, new Music("resources_/music_/catalyze.ogg", true));
                        break;
                    case "stray":
                        musicMap.put(key, new Music("resources_/music_/stray.ogg", true));
                        break;
                    case "jazz":
                        musicMap.put(key, new Music("resources_/music_/jazz.ogg", true));
                        break;
                    // the time i started to think about my existence
                    case "success":
                        musicMap.put(key, new Music("resources_/music_/success.ogg", true));
                        break;
                    case "supernova":
                        musicMap.put(key, new Music("resources_/music_/supernova.ogg", true));
                        break;
                    case "time":
                        musicMap.put(key, new Music("resources_/music_/time.ogg", true));
                        break;
                    case "marbl":
                        musicMap.put(key, new Music("resources_/music_/marbl.ogg", true));
                        break;
                    case "dreamer":
                        musicMap.put(key, new Music("resources_/music_/dreamer.ogg", true));
                        break;
                    case "ghost_house":
                        musicMap.put(key, new Music("resources_/music_/ghost house.ogg", true));
                        break;
                    case "jude":
                        musicMap.put(key, new Music("resources_/music_/jude.ogg", true));
                        break;
                    case "don't_hold_back":
                        musicMap.put(key, new Music("resources_/music_/don't hold back.ogg", true));
                        break;
                    case "ouais_ouais":
                        musicMap.put(key, new Music("resources_/music_/ouais ouais.ogg", true));
                        break;
                    case "things_that_you_do":
                        musicMap.put(key, new Music("resources_/music_/things that you do.ogg", true));
                        break;
                    case "sad_summer":
                        musicMap.put(key, new Music("resources_/music_/sad summer.ogg", true));
                        break;
                    case "wishing":
                        musicMap.put(key, new Music("resources_/music_/wishing.ogg", true));
                        break;
                    case "popo":
                        musicMap.put(key, new Music("resources_/music_/popo.ogg", true));
                        break;
                    case "ocean_biome":
                        musicMap.put(key, new Music("resources_/music_/ocean biome.ogg", true));
                        break;
                    case "snowcone":
                        musicMap.put(key, new Music("resources_/music_/snowcone.ogg", true));
                        break;
                    case "sawdust_angels":
                        musicMap.put(key, new Music("resources_/music_/sawdust angels.ogg", true));
                        break;
                    case "sky_high":
                        musicMap.put(key, new Music("resources_/music_/sky high.ogg", true));
                        break;
                    case "my_heart":
                        musicMap.put(key, new Music("resources_/music_/my heart.ogg", true));
                        break;
                    case "nekozilla":
                        musicMap.put(key, new Music("resources_/music_/nekozilla.ogg", true));
                        break;
                    case "cloud_9":
                        musicMap.put(key, new Music("resources_/music_/cloud 9.ogg", true));
                        break;
                    case "sunburst":
                        musicMap.put(key, new Music("resources_/music_/sunburst.ogg", true));
                        break;
                    case "hellcat":
                        musicMap.put(key, new Music("resources_/music_/hellcat.ogg", true));
                        break;
                    case "denise":
                        musicMap.put(key, new Music("resources_/music_/denise 102.42.ogg", true));
                        break;
                    case "dollar_needles_1":
                        musicMap.put(key, new Music("resources_/music_/dollar needles 1.ogg", true));
                        break;
                    case "dollar_needles_3":
                        musicMap.put(key, new Music("resources_/music_/dollar needles 3.ogg", true));
                        break;
                    case "behind_these_closed_doors":
                        musicMap.put(key, new Music("resources_/music_/behind these closed doors.ogg", true));
                        break;
                    case "otis_mcmusic":
                        musicMap.put(key, new Music("resources_/music_/otis mcmusic.ogg", true));
                        break;
                    case "not_for_nothing":
                        musicMap.put(key, new Music("resources_/music_/not for nothing 86.40.ogg", true));
                        break;
                    case "mind_control":
                        musicMap.put(key, new Music("resources_/music_/mind control 129.46.ogg", true));
                        break;
                    case "memories":
                        musicMap.put(key, new Music("resources_/music_/memories.ogg", true));
                        break;
                    case "2011":
                        musicMap.put(key, new Music("resources_/music_/2011 102.10.ogg", true));
                        break;
                    case "10000":
                        musicMap.put(key, new Music("resources_/music_/10000.ogg", true));
                        break;
                    case "merry_christmas":
                        musicMap.put(key, new Music("resources_/music_/merry christmas.ogg", true));
                        break;
                    case "crab_rave":
                        musicMap.put(key, new Music("resources_/music_/crab rave.ogg", true));
                        break;
                    case "insurgent":
                        musicMap.put(key, new Music("resources_/music_/insurgent.ogg", true));
                        break;
                    case "late_night_lo-fi":
                        musicMap.put(key, new Music("resources_/music_/late night lo-fi.ogg", true));
                        break;
                    case "holystone":
                        musicMap.put(key, new Music("resources_/music_/holystone.ogg", true));
                        break;
                    case "lith_harbor":
                        musicMap.put(key, new Music("resources_/music_/lith harbor.ogg", true));
                        break;
                    case "lost_in_thought":
                        musicMap.put(key, new Music("resources_/music_/lost in thought.ogg", true));
                        break;
                    case "pounce":
                        musicMap.put(key, new Music("resources_/music_/pounce.ogg", true));
                        break;
                    case "dick":
                        musicMap.put(key, new Music("resources_/music_/dick.ogg", true));
                        break;
                    case "golden_haze":
                        musicMap.put(key, new Music("resources_/music_/golden haze.ogg", true));
                        break;
                    case "grim_reaper":
                        musicMap.put(key, new Music("resources_/music_/grim reaper.ogg", true));
                        break;
                    case "highscore":
                        musicMap.put(key, new Music("resources_/music_/highscore.ogg", true));
                        break;
                    case "purity":
                        musicMap.put(key, new Music("resources_/music_/purity.ogg", true));
                        break;
                    case "yellow_orange":
                        musicMap.put(key, new Music("resources_/music_/yellow orange.ogg", true));
                        break;
                    case "surface":
                        musicMap.put(key, new Music("resources_/music_/surface.ogg", true));
                        break;
                    case "nokia_arabic":
                        musicMap.put(key, new Music("resources_/music_/nokia arabic.ogg", true));
                        break;
                    case "nrg_drink":
                        musicMap.put(key, new Music("resources_/music_/nrg drink.ogg", true));
                        break;
                    case "happy":
                        musicMap.put(key, new Music("resources_/music_/happy.ogg", true));
                        break;
                    case "moonsugar":
                        musicMap.put(key, new Music("resources_/music_/moonsugar.ogg", true));
                        break;
                    case "rubik":
                        musicMap.put(key, new Music("resources_/music_/rubik.ogg", true));
                        break;
                    case "ignition":
                        musicMap.put(key, new Music("resources_/music_/ignition.ogg", true));
                        break;
                    case "the_force":
                        musicMap.put(key, new Music("resources_/music_/the force.ogg", true));
                        break;
                    case "candyland":
                        musicMap.put(key, new Music("resources_/music_/candyland.ogg", true));
                        break;
                    case "infectious":
                        musicMap.put(key, new Music("resources_/music_/infectious.ogg", true));
                        break;
                    case "crazy":
                        musicMap.put(key, new Music("resources_/music_/crazy.ogg", true));
                        break;
                    case "race":
                        musicMap.put(key, new Music("resources_/music_/race.ogg", true));
                        break;
                    case "ice_flow":
                        musicMap.put(key, new Music("resources_/music_/ice flow.ogg", true));
                        break;
                    case "kalimba":
                        musicMap.put(key, new Music("resources_/music_/kalimba 119.70.ogg", true));
                        break;
                    case "party":
                        musicMap.put(key, new Music("resources_/music_/party.ogg", true));
                        break;
                    case "bass":
                        musicMap.put(key, new Music("resources_/music_/bass.ogg", true));
                        break;
                    case "hazmat":
                        musicMap.put(key, new Music("resources_/music_/hazmat.ogg", true));
                        break;
                    case "panda_dance":
                        musicMap.put(key, new Music("resources_/music_/panda dance.ogg", true));
                        break;
                    case "get_up":
                        musicMap.put(key, new Music("resources_/music_/get up.ogg", true));
                        break;
                    case "river":
                        musicMap.put(key, new Music("resources_/music_/river.ogg", true));
                        break;
                    case "follow":
                        musicMap.put(key, new Music("resources_/music_/follow.ogg", true));
                        break;
                    case "slime":
                        musicMap.put(key, new Music("resources_/music_/slime.ogg", true));
                        break;
                    case "euphoria":
                        musicMap.put(key, new Music("resources_/music_/euphoria.ogg", true));
                        break;
                    case "nautilus":
                        musicMap.put(key, new Music("resources_/music_/nautilus.ogg", true));
                        break;
                    case "liaquo":
                        musicMap.put(key, new Music("resources_/music_/lia quo.ogg", true));
                        break;
                    case "crazy_frog":
                        musicMap.put(key, new Music("resources_/music_/crazy frog.ogg", true));
                        break;
                    case "never_lose_hope":
                        musicMap.put(key, new Music("resources_/music_/never lose hope.ogg", true));
                        break;
                    case "skystrike":
                        musicMap.put(key, new Music("resources_/music_/skystrike.ogg", true));
                        break;
                    case "starchaser":
                        musicMap.put(key, new Music("resources_/music_/starchaser.ogg", true));
                        break;
                    case "a_newer_dawn":
                        musicMap.put(key, new Music("resources_/music_/a newer dawn.ogg", true));
                        break;
                    case "monody":
                        musicMap.put(key, new Music("resources_/music_/monody.ogg", true));
                        break;
                    case "unity":
                        musicMap.put(key, new Music("resources_/music_/unity.ogg", true));
                        break;
                    case "xenogenesis":
                        musicMap.put(key, new Music("resources_/music_/xenogenesis.ogg", true));
                        break;
                    case "time_stops":
                        musicMap.put(key, new Music("resources_/music_/time stops.ogg", true));
                        break;
                    case "badland":
                        musicMap.put(key, new Music("resources_/music_/badland.ogg", true));
                        break;
                    case "challenger":
                        musicMap.put(key, new Music("resources_/music_/challenger.ogg", true));
                        break;
                    case "bluemoon":
                        musicMap.put(key, new Music("resources_/music_/bluemoon 112.50.ogg", true));
                        break;
                    case "cherry_blossoms":
                        musicMap.put(key, new Music("resources_/music_/cherry blossoms.ogg", true));
                        break;
                    case "walkman":
                        musicMap.put(key, new Music("resources_/music_/walkman.ogg", true));
                        break;
                    case "beyond_the_walls":
                        musicMap.put(key, new Music("resources_/music_/beyond the walls.ogg", true));
                        break;
                    case "april":
                        musicMap.put(key, new Music("resources_/music_/april.ogg", true));
                        break;
                    case "sunlight":
                        musicMap.put(key, new Music("resources_/music_/sunlight.ogg", true));
                        break;
                    case "cyptik_dance":
                        musicMap.put(key, new Music("resources_/music_/cryptik dance.ogg", true));
                        break;
                    case "leaving_leafwood_forest":
                        musicMap.put(key, new Music("resources_/music_/leaving leafwood forest.ogg", true));
                        break;
                    case "troglodyte":
                        musicMap.put(key, new Music("resources_/music_/troglodyte.ogg", true));
                        break;
                    case "mayo":
                        musicMap.put(key, new Music("resources_/music_/mayo.ogg", true));
                        break;
                    case "starship_showdown":
                        musicMap.put(key, new Music("resources_/music_/starship showdown.ogg", true));
                        break;
                    case "kumquat":
                        musicMap.put(key, new Music("resources_/music_/kumquat.ogg", true));
                        break;
                    case "jaclyn":
                        musicMap.put(key, new Music("resources_/music_/jaclyn.ogg", true));
                        break;
                    case "jacques":
                        musicMap.put(key, new Music("resources_/music_/jacques.ogg", true));
                        break;
                    case "liftoff":
                        musicMap.put(key, new Music("resources_/music_/liftoff.ogg", true));
                        break;
                    case "never_be_alone":
                        musicMap.put(key, new Music("resources_/music_/never be alone.ogg", true));
                        break;
                    case "solitude":
                        musicMap.put(key, new Music("resources_/music_/solitude.ogg", true));
                        break;
                    case "close_to_the_sun":
                        musicMap.put(key, new Music("resources_/music_/close to the sun.ogg", true));
                        break;
                    case "nanamori":
                        musicMap.put(key, new Music("resources_/music_/nanamori.ogg", true));
                        break;
                    case "fury":
                        musicMap.put(key, new Music("resources_/music_/fury.ogg", true));
                        break;
                    case "desu_ka":
                        musicMap.put(key, new Music("resources_/music_/desu ka.ogg", true));
                        break;
                    case "voices":
                        musicMap.put(key, new Music("resources_/music_/voices.ogg", true));
                        break;
                    case "dancing":
                        musicMap.put(key, new Music("resources_/music_/dancing.ogg", true));
                        break;
                    case "shining_space":
                        musicMap.put(key, new Music("resources_/music_/shining space.ogg", true));
                        break;
                    case "space_invaders":
                        musicMap.put(key, new Music("resources_/music_/space invaders.ogg", true));
                        break;
                    case "drippy_dub":
                        musicMap.put(key, new Music("resources_/music_/drippy dub.ogg", true));
                        break;
                    case "fake_princess":
                        musicMap.put(key, new Music("resources_/music_/fake princess.ogg", true));
                        break;
                    case "lazergun":
                        musicMap.put(key, new Music("resources_/music_/lazergun.ogg", true));
                        break;
                    case "afterimage":
                        musicMap.put(key, new Music("resources_/music_/afterimage.ogg", true));
                        break;
                    case "star_wars_remix":
                        musicMap.put(key, new Music("resources_/music_/star wars remix.ogg", true));
                        break;
                    case "kiss_me_kiss_you":
                        musicMap.put(key, new Music("resources_/music_/kiss me kiss you.ogg", true));
                        break;
                    case "enigma":
                        musicMap.put(key, new Music("resources_/music_/enigma.ogg", true));
                        break;
                    case "never_make_it":
                        musicMap.put(key, new Music("resources_/music_/never make it.ogg", true));
                        break;
                    case "flight":
                        musicMap.put(key, new Music("resources_/music_/flight.ogg", true));
                        break;
                    case "journey":
                        musicMap.put(key, new Music("resources_/music_/journey.ogg", true));
                        break;
                    case "lonley_forest":
                        musicMap.put(key, new Music("resources_/music_/lonley forest.ogg", true));
                        break;
                    case "pirate":
                        musicMap.put(key, new Music("resources_/music_/pirate.ogg", true));
                        break;
                    case "aquamarine":
                        musicMap.put(key, new Music("resources_/music_/aquamarine.ogg", true));
                        break;
                    case "boombox":
                        musicMap.put(key, new Music("resources_/music_/boombox.ogg", true));
                        break;
                    case "niconico_dreams":
                        musicMap.put(key, new Music("resources_/music_/niconico dreams.ogg", true));
                        break;
                    case "requiem_dream":
                        musicMap.put(key, new Music("resources_/music_/requiem dream.ogg", true));
                        break;
                    case "eurodancer":
                        musicMap.put(key, new Music("resources_/music_/eurodancer.ogg", true));
                        break;
                    case "hello":
                        musicMap.put(key, new Music("resources_/music_/hello.ogg", true));
                        break;
                    case "mayday":
                        musicMap.put(key, new Music("resources_/music_/mayday.ogg", true));
                        break;
                    case "fireflies_remix":
                        musicMap.put(key, new Music("resources_/music_/fireflies remix.ogg", true));
                        break;
                    case "echolands":
                        musicMap.put(key, new Music("resources_/music_/echolands.ogg", true));
                        break;
                    case "voiceless":
                        musicMap.put(key, new Music("resources_/music_/voiceless.ogg", true));
                        break;
                    case "vaporwave_aesthetics":
                        musicMap.put(key, new Music("resources_/music_/vaporwave aesthetics.ogg", true));
                        break;
                    case "happy_troll":
                        musicMap.put(key, new Music("resources_/music_/happy troll.ogg", true));
                        break;
                    case "dimension":
                        musicMap.put(key, new Music("resources_/music_/dimension.ogg", true));
                        break;
                    case "crystal_caves":
                        musicMap.put(key, new Music("resources_/music_/crystal caves.ogg", true));
                        break;
                    case "elevate":
                        musicMap.put(key, new Music("resources_/music_/elevate.ogg", true));
                        break;
                    case "okiba":
                        musicMap.put(key, new Music("resources_/music_/okiba.ogg", true));
                        break;
                    case "falling_mysts":
                        musicMap.put(key, new Music("resources_/music_/falling mysts.ogg", true));
                        break;
                    case "newgrounds_return":
                        musicMap.put(key, new Music("resources_/music_/newgrounds return.ogg", true));
                        break;
                    case "body_jammer":
                        musicMap.put(key, new Music("resources_/music_/body jammer.ogg", true));
                        break;
                    case "flirt":
                        musicMap.put(key, new Music("resources_/music_/flirt.ogg", true));
                        break;
                    case "retry":
                        musicMap.put(key, new Music("resources_/music_/retry.ogg", true));
                        break;
                    case "jet_set":
                        musicMap.put(key, new Music("resources_/music_/jet set.ogg", true));
                        break;
                    case "the_calling":
                        musicMap.put(key, new Music("resources_/music_/the calling.ogg", true));
                        break;
                    case "tria":
                        musicMap.put(key, new Music("resources_/music_/tria.ogg", true));
                        break;
                    case "endgame":
                        musicMap.put(key, new Music("resources_/music_/endgame.ogg", true));
                        break;
                    case "night_out":
                        musicMap.put(key, new Music("resources_/music_/night out.ogg", true));
                        break;
                    case "april_showers":
                        musicMap.put(key, new Music("resources_/music_/april showers.ogg", true));
                        break;
                    case "bathtub":
                        musicMap.put(key, new Music("resources_/music_/bathtub.ogg", true));
                        break;
                    case "laszlo":
                        musicMap.put(key, new Music("resources_/music_/laszlo.ogg", true));
                        break;
                    case "force":
                        musicMap.put(key, new Music("resources_/music_/force.ogg", true));
                        break;
                    case "spectre":
                        musicMap.put(key, new Music("resources_/music_/spectre.ogg", true));
                        break;
                    case "fade":
                        musicMap.put(key, new Music("resources_/music_/fade.ogg", true));
                        break;
                    case "bangarang":
                        musicMap.put(key, new Music("resources_/music_/bangarang.ogg", true));
                        break;
                    case "bun_dem":
                        musicMap.put(key, new Music("resources_/music_/bun dem.ogg", true));
                        break;
                    case "sleepyhead":
                        musicMap.put(key, new Music("resources_/music_/sleepyhead.ogg", true));
                        break;
                    case "dance_till_you're_dead":
                        musicMap.put(key, new Music("resources_/music_/dance till you're dead.ogg", true));
                        break;
                    case "animals":
                        musicMap.put(key, new Music("resources_/music_/animals.ogg", true));
                        break;
                    case "yeah":
                        musicMap.put(key, new Music("resources_/music_/yeah.ogg", true));
                        break;
                    case "november":
                        musicMap.put(key, new Music("resources_/music_/november.ogg", true));
                        break;
                    case "florescence":
                        musicMap.put(key, new Music("resources_/music_/florescence.ogg", true));
                        break;
                    case "mantis_shrimp_showdown":
                        musicMap.put(key, new Music("resources_/music_/mantis shrimp showdown.ogg", true));
                        break;
                    case "holy_war":
                        musicMap.put(key, new Music("resources_/music_/holy war.ogg", true));
                        break;
                    case "massacre":
                        musicMap.put(key, new Music("resources_/music_/massacre.ogg", true));
                        break;
                    case "believe":
                        musicMap.put(key, new Music("resources_/music_/believe.ogg", true));
                        break;
                    case "karma":
                        musicMap.put(key, new Music("resources_/music_/karma.ogg", true));
                        break;
                    case "refraction":
                        musicMap.put(key, new Music("resources_/music_/refraction.ogg", true));
                        break;
                    case "glome":
                        musicMap.put(key, new Music("resources_/music_/glome.ogg", true));
                        break;
                    case "red_roses":
                        musicMap.put(key, new Music("resources_/music_/red roses.ogg", true));
                        break;
                    case "a_little_older":
                        musicMap.put(key, new Music("resources_/music_/a little older.ogg", true));
                        break;
                    case "when_time_tears":
                        musicMap.put(key, new Music("resources_/music_/when time tears.ogg", true));
                        break;
                    case "hold_on_to_me":
                        musicMap.put(key, new Music("resources_/music_/hold on to me.ogg", true));
                        break;
                    case "i'm_still_alive":
                        musicMap.put(key, new Music("resources_/music_/i'm still alive.ogg", true));
                        break;
                    case "nirmiti":
                        musicMap.put(key, new Music("resources_/music_/nirmiti.ogg", true));
                        break;
                    case "avast_your_ass":
                        musicMap.put(key, new Music("resources_/music_/avast your ass.ogg", true));
                        break;
                    case "soulwind":
                        musicMap.put(key, new Music("resources_/music_/soulwind.ogg", true));
                        break;
                    case "zenith":
                        musicMap.put(key, new Music("resources_/music_/zenith.ogg", true));
                        break;
                    case "portals":
                        musicMap.put(key, new Music("resources_/music_/portals.ogg", true));
                        break;
                    case "mellow":
                        musicMap.put(key, new Music("resources_/music_/mellow.ogg", true));
                        break;
                    case "lullaby_remix":
                        musicMap.put(key, new Music("resources_/music_/lullaby remix.ogg", true));
                        break;
                    case "florescentia":
                        musicMap.put(key, new Music("resources_/music_/florescentia.ogg", true));
                        break;
                    case "bioluminescent":
                        musicMap.put(key, new Music("resources_/music_/bioluminescent.ogg", true));
                        break;
                    case "let's_bounce":
                        musicMap.put(key, new Music("resources_/music_/let's bounce.ogg", true));
                        break;
                    case "orbit":
                        musicMap.put(key, new Music("resources_/music_/orbit.ogg", true));
                        break;
                    case "luminous":
                        musicMap.put(key, new Music("resources_/music_/luminous.ogg", true));
                        break;
                    case "mako":
                        musicMap.put(key, new Music("resources_/music_/mako.ogg", true));
                        break;
                    case "love_talk":
                        musicMap.put(key, new Music("resources_/music_/love talk.ogg", true));
                        break;
                    case "trouble":
                        musicMap.put(key, new Music("resources_/music_/trouble.ogg", true));
                        break;
                    case "peepee":
                        musicMap.put(key, new Music("resources_/music_/peepee.ogg", true));
                        break;
                    case "rose":
                        musicMap.put(key, new Music("resources_/music_/rose.ogg", true));
                        break;
                    case "fateful_mist":
                        musicMap.put(key, new Music("resources_/music_/fateful mist.ogg", true));
                        break;
                    case "flashyizz_crazy":
                        musicMap.put(key, new Music("resources_/music_/flashyizz crazy.ogg", true));
                        break;
                    case "glorious_morning":
                        musicMap.put(key, new Music("resources_/music_/glorious morning.ogg", true));
                        break;
                    case "flyboy_and_gabbergirl":
                        musicMap.put(key, new Music("resources_/music_/flyboy and gabbergirl.ogg", true));
                        break;
                    case "battletown":
                        musicMap.put(key, new Music("resources_/music_/battletown.ogg", true));
                        break;
                    case "nanobyte":
                        musicMap.put(key, new Music("resources_/music_/nanobyte.ogg", true));
                        break;
                    case "fantasy":
                        musicMap.put(key, new Music("resources_/music_/fantasy.ogg", true));
                        break;
                    case "artificial":
                        musicMap.put(key, new Music("resources_/music_/artificial.ogg", true));
                        break;
                    case "run_away":
                        musicMap.put(key, new Music("resources_/music_/run away.ogg", true));
                        break;
                    case "dream":
                        musicMap.put(key, new Music("resources_/music_/dream.ogg", true));
                        break;
                    case "drained":
                        musicMap.put(key, new Music("resources_/music_/drained.ogg", true));
                        break;
                    case "just_kidding":
                        musicMap.put(key, new Music("resources_/music_/just kidding.ogg", true));
                        break;
                    case "saunter":
                        musicMap.put(key, new Music("resources_/music_/saunter.ogg", true));
                        break;
                    case "help_me_up":
                        musicMap.put(key, new Music("resources_/music_/help me up.ogg", true));
                        break;
                    case "ocean_of_stars":
                        musicMap.put(key, new Music("resources_/music_/ocean of stars.ogg", true));
                        break;
                    case "bash":
                        musicMap.put(key, new Music("resources_/music_/bash.ogg", true));
                        break;
                    case "whirlwind":
                        musicMap.put(key, new Music("resources_/music_/whirlwind.ogg", true));
                        break;
                    case "screamroom":
                        musicMap.put(key, new Music("resources_/music_/screamroom.ogg", true));
                        break;
                    case "ichor":
                        musicMap.put(key, new Music("resources_/music_/ichor.ogg", true));
                        break;
                    case "time_lapse":
                        musicMap.put(key, new Music("resources_/music_/time lapse.ogg", true));
                        break;
                    case "calm":
                        musicMap.put(key, new Music("resources_/music_/calm.ogg", true));
                        break;
                    case "gloomy":
                        musicMap.put(key, new Music("resources_/music_/gloomy.ogg", true));
                        break;
                    case "love's_song":
                        musicMap.put(key, new Music("resources_/music_/love's song.ogg", true));
                        break;
                    case "blast_em":
                        musicMap.put(key, new Music("resources_/music_/blast em.ogg", true));
                        break;
                    case "lost_in_the_rhythm":
                        musicMap.put(key, new Music("resources_/music_/lost in the rhythm.ogg", true));
                        break;
                    case "zelda_palace":
                        musicMap.put(key, new Music("resources_/music_/zelda palace.ogg", true));
                        break;
                    case "form":
                        musicMap.put(key, new Music("resources_/music_/form.ogg", true));
                        break;
                    case "paladin":
                        musicMap.put(key, new Music("resources_/music_/paladin.ogg", true));
                        break;
                    case "myriad":
                        musicMap.put(key, new Music("resources_/music_/myriad.ogg", true));
                        break;
                    case "pyrolysis":
                        musicMap.put(key, new Music("resources_/music_/pyrolysis.ogg", true));
                        break;
                    case "elevatia":
                        musicMap.put(key, new Music("resources_/music_/elevatia.ogg", true));
                        break;
                    case "prelude_remix":
                        musicMap.put(key, new Music("resources_/music_/prelude remix.ogg", true));
                        break;
                    case "could_it_be":
                        musicMap.put(key, new Music("resources_/music_/could it be.ogg", true));
                        break;
                    case "spooky":
                        musicMap.put(key, new Music("resources_/music_/spooky.ogg", true));
                        break;
                    case "ena":
                        musicMap.put(key, new Music("resources_/music_/ena.ogg", true));
                        break;
                    case "reapers":
                        musicMap.put(key, new Music("resources_/music_/reapers.ogg", true));
                        break;
                    case "last_dance":
                        musicMap.put(key, new Music("resources_/music_/last dance.ogg", true));
                        break;
                    case "reminisce":
                        musicMap.put(key, new Music("resources_/music_/reminisce.ogg", true));
                        break;
                    case "who_you_are":
                        musicMap.put(key, new Music("resources_/music_/who you are.ogg", true));
                        break;
                    case "stardrive":
                        musicMap.put(key, new Music("resources_/music_/stardrive.ogg", true));
                        break;
                    case "level_one":
                        musicMap.put(key, new Music("resources_/music_/level one.ogg", true));
                        break;
                    case "lights":
                        musicMap.put(key, new Music("resources_/music_/lights.ogg", true));
                        break;
                    case "skybound":
                        musicMap.put(key, new Music("resources_/music_/skybound.ogg", true));
                        break;
                    case "open_your_eyes":
                        musicMap.put(key, new Music("resources_/music_/open your eyes.ogg", true));
                        break;
                    case "party_hard_remix":
                        musicMap.put(key, new Music("resources_/music_/party hard remix.ogg", true));
                        break;
                    case "nice_vibes":
                        musicMap.put(key, new Music("resources_/music_/nice vibes.ogg", true));
                        break;
                    case "horizons_remix":
                        musicMap.put(key, new Music("resources_/music_/horizons remix.ogg", true));
                        break;
                    case "ablixa":
                        musicMap.put(key, new Music("resources_/music_/ablixa.ogg", true));
                        break;
                    case "untitled":
                        musicMap.put(key, new Music("resources_/music_/untitled.ogg", true));
                        break;
                    case "litoff":
                        musicMap.put(key, new Music("resources_/music_/litoff.ogg", true));
                        break;
                    case "nuetronium":
                        musicMap.put(key, new Music("resources_/music_/nuetronium.ogg", true));
                        break;
                    case "sky_venture":
                        musicMap.put(key, new Music("resources_/music_/sky venture.ogg", true));
                        break;
                    case "sad_machine_remix":
                        musicMap.put(key, new Music("resources_/music_/sad machine remix.ogg", true));
                        break;
                    case "jazz_jackrabbit_remix":
                        musicMap.put(key, new Music("resources_/music_/jazz jackrabbit remix.ogg", true));
                        break;
                    case "shadow_queen_part_2_remix":
                        musicMap.put(key, new Music("resources_/music_/shadow queen part 2 remix.ogg", true));
                        break;
                    case "let's_stomp":
                        musicMap.put(key, new Music("resources_/music_/let's stomp.ogg", true));
                        break;
                    case "twinrova":
                        musicMap.put(key, new Music("resources_/music_/twinrova.ogg", true));
                        break;
                    case "our_home":
                        musicMap.put(key, new Music("resources_/music_/our home.ogg", true));
                        break;
                    case "geometry_dance":
                        musicMap.put(key, new Music("resources_/music_/geometry dance.ogg", true));
                        break;
                    case "this_is_geometry_dash":
                        musicMap.put(key, new Music("resources_/music_/this is geometry dash.ogg", true));
                        break;
                    case "waves":
                        musicMap.put(key, new Music("resources_/music_/waves.ogg", true));
                        break;
                    case "among_us_trap":
                        musicMap.put(key, new Music("resources_/music_/among us trap.ogg", true));
                        break;
                    case "legend":
                        musicMap.put(key, new Music("resources_/music_/legend.ogg", true));
                        break;
                    case "this_girl":
                        musicMap.put(key, new Music("resources_/music_/this girl.ogg", true));
                        break;
                    case "sunwalker":
                        musicMap.put(key, new Music("resources_/music_/sunwalker.ogg", true));
                        break;
                    case "afterglow":
                        musicMap.put(key, new Music("resources_/music_/afterglow.ogg", true));
                        break;
                    case "a_cow_that_is_actually_a_chicken":
                        musicMap.put(key, new Music("resources_/music_/a cow that is actually a chicken.ogg", true));
                        break;
                    case "ストーカー":
                        musicMap.put(key, new Music("resources_/music_/ストーカー.ogg", true));
                        break;

                    // template
                    /*case "null":
                        musicMap.put(key, new Music("resources_/music_/null.ogg", true));
                        break;*/
                }
            }
            // edit: use GOXR3PLUS STUDIO code for this
        } catch (SlickException e) {
            e.printStackTrace();
            int a = JOptionPane.showConfirmDialog(null, "An error occurred: \n" + e + ", \ndo you still wish to continue?", "Error", JOptionPane.INFORMATION_MESSAGE);
            if (a == JOptionPane.NO_OPTION) System.exit(0);
        }
    }

    // FIXED: fix getSound linked to game_.sfx
    public static Sound getSound (String key) {
        return soundMap.get(key);
    }

    public static AudioInputStream audioInputStream;
    public static Music getMusic (String key) {
        // load codes
        if (key != "music" && key != "null" && key != "shop_music" && key != "game_over"
                && game_.gameState != STATE.GameBeta /*culprit*/) {
            // assign current music
            currentMusic = key;
            // load music
            game_.gameState = STATE.Load;
            game_.stringsforloading = "loading music: " + key;
            game_.loadstate -= 25;
            load(key);
            game_.loadstate += 25;
            /*try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("resources_/music_/waves.ogg"));
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        // reset poll
        lastFrame = getTime();
        delta = getDelta();
        // return music
        return musicMap.get(key);
    }

    // beads random generated music
    static AudioContext ac;
    public static void playRandomGenMusic(){
        if (ac != null)
            ac.stop();
        // use this for java 11
        /*JavaSoundAudioIO io = new JavaSoundAudioIO();
        io.selectMixer(2);
        ac = new AudioContext(io);*/

        // use this for java 8
        ac = new AudioContext();

        /*
         * In this example a Clock is used to trigger events. We do this by
         * adding a listener to the Clock (which is of type Bead).
         *
         * The Bead is made on-the-fly. All we have to do is to give the Bead a
         * callback method to make notes.
         *
         * This example is more sophisticated than the previous ones. It uses
         * nested code.
         */
        Clock clock = new Clock(ac, 700);
        clock.addMessageListener(
                //this is the on-the-fly bead
                new Bead() {
                    //this is the method that we override to make the Bead do something
                    int pitch;
                    public void messageReceived(Bead message) {
                        Clock c = (Clock)message;
                        if(c.isBeat()) {
                            //choose some nice frequencies
                            if(random(1) < 0.5) return;
                            pitch = Pitch.forceToScale((int)random(12), Pitch.dorian);
                            float freq = Pitch.mtof(pitch + (int)random(5) * 12 + 32);
                            WavePlayer wp = new WavePlayer(ac, freq, Buffer.SINE);
                            Gain g = new Gain(ac, 1, new Envelope(ac, 0));
                            g.addInput(wp);
                            ac.out.addInput(g);
                            ((Envelope)g.getGainUGen()).addSegment(0.1f, random(200));
                            ((Envelope)g.getGainUGen()).addSegment(0, random(7000), new KillTrigger(g));
                        }
                        if(c.getCount() % 4 == 0) {
                            //choose some nice frequencies
                            int pitchAlt = pitch;
                            if(random(1) < 0.2) pitchAlt = Pitch.forceToScale((int)random(12), Pitch.dorian) + (int)random(2) * 12;
                            float freq = Pitch.mtof(pitchAlt + 32);
                            WavePlayer wp = new WavePlayer(ac, freq, Buffer.SQUARE);
                            Gain g = new Gain(ac, 1, new Envelope(ac, 0));
                            g.addInput(wp);
                            Panner p = new Panner(ac, random(1));
                            p.addInput(g);
                            ac.out.addInput(p);
                            ((Envelope)g.getGainUGen()).addSegment(random(0.1), random(50));
                            ((Envelope)g.getGainUGen()).addSegment(0, random(400), new KillTrigger(p));
                        }
                        if(c.getCount() % 4 == 0) {
                            Noise n = new Noise(ac);
                            Gain g = new Gain(ac, 1, new Envelope(ac, 0.05f));
                            g.addInput(n);
                            Panner p = new Panner(ac, random(0.5) + 0.5f);
                            p.addInput(g);
                            ac.out.addInput(p);
                            ((Envelope)g.getGainUGen()).addSegment(0, random(100), new KillTrigger(p));
                        }
                    }
                }
        );
        ac.out.addDependent(clock);
        ac.out.setGain(4);
        ac.start();
    }
    public static void stopRandomGenMusic(){
        if (ac != null) ac.stop();
    }

    public static float random(double x) {
        return (float)(Math.random() * x);
    }

    // thread for Music.poll(int delta)
    Thread thread;
    boolean running = false;
    // making Music.poll(int delta) a thing
    static long lastFrame;
    static int delta;

    public void initPoll(){
        start();
    }
    @Override
    public void run() {
        lastFrame = getTime();
        while (running) {
            delta = getDelta();
            // FIXME: too many statements
            if (gameState == STATE.GameBeta && audioplayer_.currentMusic != "" && audioplayer_.getMusic(currentMusic).playing())
                Music.poll(delta);
        }
    }
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }
    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
