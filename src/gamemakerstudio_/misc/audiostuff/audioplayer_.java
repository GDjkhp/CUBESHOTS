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
public class audioplayer_ {
    
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
            soundMap.put("death", new Sound("resources_/sounds_/death.ogg"));
            soundMap.put("win_1", new Sound("resources_/sounds_/win_1.ogg"));
            soundMap.put("win_2", new Sound("resources_/sounds_/win_2.ogg"));
            soundMap.put("alert", new Sound("resources_/sounds_/alert.ogg"));
            soundMap.put("laser", new Sound("resources_/sounds_/ChipShop_GB_FX_14.ogg"));
            soundMap.put("boom", new Sound("resources_/sounds_/ChipShop_YM_Noise_04.ogg"));
            // new code for custom music
            if (!betaAudioEngine) {
                switch (key) {
                    case "record":
                        musicMap.put(key, new Music("C:\\Users\\ACER\\Desktop\\stuff goes here\\" + record + ".ogg"));
                        break;
                    case "dead_meme":
                        musicMap.put(key, new Music("resources_/music_/dead meme.ogg"));
                        break;
                    case "dead_meme_2":
                        musicMap.put(key, new Music("resources_/music_/dead meme 2.ogg"));
                        break;
                    case "eschew":
                        musicMap.put(key, new Music("resources_/music_/eschew.ogg"));
                        break;
                    case "rock_the_house":
                        musicMap.put(key, new Music("resources_/music_/rock the house.ogg"));
                        break;
                    case "end_of_time":
                        musicMap.put(key, new Music("resources_/music_/end of time.ogg"));
                        break;
                    case "nova_music":
                        musicMap.put(key, new Music("resources_/music_/nova music.ogg"));
                        break;
                    case "time_leaper":
                        musicMap.put(key, new Music("resources_/music_/time leaper.ogg"));
                        break;
                    case "gg":
                        musicMap.put(key, new Music("resources_/music_/gg.ogg"));
                        break;
                    case "namice1":
                        musicMap.put(key, new Music("resources_/music_/namice1.ogg"));
                        break;
                    case "namice2":
                        musicMap.put(key, new Music("resources_/music_/namice2.ogg"));
                        break;
                    case "namice3":
                        musicMap.put(key, new Music("resources_/music_/namice3.ogg"));
                        break;
                    case "there":
                        musicMap.put(key, new Music("resources_/music_/there.ogg"));
                        break;
                    case "fisher_price":
                        musicMap.put(key, new Music("resources_/music_/fisher price.ogg"));
                        break;
                    case "dance_violins":
                        musicMap.put(key, new Music("resources_/music_/dance violins.ogg"));
                        break;
                    // new
                    case "aether":
                        musicMap.put(key, new Music("resources_/music_/aether.ogg"));
                        break;
                    case "clickbait":
                        musicMap.put(key, new Music("resources_/music_/clickbait.ogg"));
                        break;
                    case "debug":
                        musicMap.put(key, new Music("resources_/music_/debug.ogg"));
                        break;
                    case "iy":
                        musicMap.put(key, new Music("resources_/music_/iy.ogg"));
                        break;
                    case "ye":
                        musicMap.put(key, new Music("resources_/music_/ye.ogg"));
                        break;
                    case "mocha":
                        musicMap.put(key, new Music("resources_/music_/mocha.ogg"));
                        break;
                    case "everything_falls":
                        musicMap.put(key, new Music("resources_/music_/everything falls.ogg"));
                        break;
                    case "lonely_forest":
                        musicMap.put(key, new Music("resources_/music_/lonely forest.ogg"));
                        break;
                    case "saxophone":
                        musicMap.put(key, new Music("resources_/music_/saxophone.ogg"));
                        break;
                    case "overcharge":
                        musicMap.put(key, new Music("resources_/music_/overcharge.ogg"));
                        break;
                    case "flavored_ice":
                        musicMap.put(key, new Music("resources_/music_/flavored ice.ogg"));
                        break;
                    case "shape_of_the_sun":
                        musicMap.put(key, new Music("resources_/music_/shape of the sun.ogg"));
                        break;
                    case "angels":
                        musicMap.put(key, new Music("resources_/music_/angels.ogg"));
                        break;
                    case "perseverance":
                        musicMap.put(key, new Music("resources_/music_/perseverance.ogg"));
                        break;
                    case "dynasty":
                        musicMap.put(key, new Music("resources_/music_/dynasty.ogg"));
                        break;
                    case "bloom":
                        musicMap.put(key, new Music("resources_/music_/bloom.ogg"));
                        break;
                    case "canyon":
                        musicMap.put(key, new Music("resources_/music_/canyon.ogg"));
                        break;
                    case "overcloud":
                        musicMap.put(key, new Music("resources_/music_/overcloud.ogg"));
                        break;
                    case "ppaper_pplanes":
                        musicMap.put(key, new Music("resources_/music_/ppaper pplanes.ogg"));
                        break;
                    case "prelude":
                        musicMap.put(key, new Music("resources_/music_/prelude.ogg"));
                        break;
                    case "spirit":
                        musicMap.put(key, new Music("resources_/music_/spirit.ogg"));
                        break;
                    case "catalyze":
                        musicMap.put(key, new Music("resources_/music_/catalyze.ogg"));
                        break;
                    case "stray":
                        musicMap.put(key, new Music("resources_/music_/stray.ogg"));
                        break;
                    case "jazz":
                        musicMap.put(key, new Music("resources_/music_/jazz.ogg"));
                        break;
                    // the time i started to think about my existence
                    case "success":
                        musicMap.put(key, new Music("resources_/music_/success.ogg"));
                        break;
                    case "supernova":
                        musicMap.put(key, new Music("resources_/music_/supernova.ogg"));
                        break;
                    case "time":
                        musicMap.put(key, new Music("resources_/music_/time.ogg"));
                        break;
                    case "marbl":
                        musicMap.put(key, new Music("resources_/music_/marbl.ogg"));
                        break;
                    case "dreamer":
                        musicMap.put(key, new Music("resources_/music_/dreamer.ogg"));
                        break;
                    case "ghost_house":
                        musicMap.put(key, new Music("resources_/music_/ghost house.ogg"));
                        break;
                    case "jude":
                        musicMap.put(key, new Music("resources_/music_/jude.ogg"));
                        break;
                    case "don't_hold_back":
                        musicMap.put(key, new Music("resources_/music_/don't hold back.ogg"));
                        break;
                    case "ouais_ouais":
                        musicMap.put(key, new Music("resources_/music_/ouais ouais.ogg"));
                        break;
                    case "things_that_you_do":
                        musicMap.put(key, new Music("resources_/music_/things that you do.ogg"));
                        break;
                    case "sad_summer":
                        musicMap.put(key, new Music("resources_/music_/sad summer.ogg"));
                        break;
                    case "wishing":
                        musicMap.put(key, new Music("resources_/music_/wishing.ogg"));
                        break;
                    case "popo":
                        musicMap.put(key, new Music("resources_/music_/popo.ogg"));
                        break;
                    case "ocean_biome":
                        musicMap.put(key, new Music("resources_/music_/ocean biome.ogg"));
                        break;
                    case "snowcone":
                        musicMap.put(key, new Music("resources_/music_/snowcone.ogg"));
                        break;
                    case "sawdust_angels":
                        musicMap.put(key, new Music("resources_/music_/sawdust angels.ogg"));
                        break;
                    case "sky_high":
                        musicMap.put(key, new Music("resources_/music_/sky high.ogg"));
                        break;
                    case "my_heart":
                        musicMap.put(key, new Music("resources_/music_/my heart.ogg"));
                        break;
                    case "nekozilla":
                        musicMap.put(key, new Music("resources_/music_/nekozilla.ogg"));
                        break;
                    case "cloud_9":
                        musicMap.put(key, new Music("resources_/music_/cloud 9.ogg"));
                        break;
                    case "sunburst":
                        musicMap.put(key, new Music("resources_/music_/sunburst.ogg"));
                        break;
                    case "hellcat":
                        musicMap.put(key, new Music("resources_/music_/hellcat.ogg"));
                        break;
                    case "denise":
                        musicMap.put(key, new Music("resources_/music_/denise 102.42.ogg"));
                        break;
                    case "dollar_needles_1":
                        musicMap.put(key, new Music("resources_/music_/dollar needles 1.ogg"));
                        break;
                    case "dollar_needles_3":
                        musicMap.put(key, new Music("resources_/music_/dollar needles 3.ogg"));
                        break;
                    case "behind_these_closed_doors":
                        musicMap.put(key, new Music("resources_/music_/behind these closed doors.ogg"));
                        break;
                    case "otis_mcmusic":
                        musicMap.put(key, new Music("resources_/music_/otis mcmusic.ogg"));
                        break;
                    case "not_for_nothing":
                        musicMap.put(key, new Music("resources_/music_/not for nothing 86.40.ogg"));
                        break;
                    case "mind_control":
                        musicMap.put(key, new Music("resources_/music_/mind control 129.46.ogg"));
                        break;
                    case "memories":
                        musicMap.put(key, new Music("resources_/music_/memories.ogg"));
                        break;
                    case "2011":
                        musicMap.put(key, new Music("resources_/music_/2011 102.10.ogg"));
                        break;
                    case "10000":
                        musicMap.put(key, new Music("resources_/music_/10000.ogg"));
                        break;
                    case "merry_christmas":
                        musicMap.put(key, new Music("resources_/music_/merry christmas.ogg"));
                        break;
                    case "crab_rave":
                        musicMap.put(key, new Music("resources_/music_/crab rave.ogg"));
                        break;
                    case "insurgent":
                        musicMap.put(key, new Music("resources_/music_/insurgent.ogg"));
                        break;
                    case "late_night_lo-fi":
                        musicMap.put(key, new Music("resources_/music_/late night lo-fi.ogg"));
                        break;
                    case "holystone":
                        musicMap.put(key, new Music("resources_/music_/holystone.ogg"));
                        break;
                    case "lith_harbor":
                        musicMap.put(key, new Music("resources_/music_/lith harbor.ogg"));
                        break;
                    case "lost_in_thought":
                        musicMap.put(key, new Music("resources_/music_/lost in thought.ogg"));
                        break;
                    case "pounce":
                        musicMap.put(key, new Music("resources_/music_/pounce.ogg"));
                        break;
                    case "dick":
                        musicMap.put(key, new Music("resources_/music_/dick.ogg"));
                        break;
                    case "golden_haze":
                        musicMap.put(key, new Music("resources_/music_/golden haze.ogg"));
                        break;
                    case "grim_reaper":
                        musicMap.put(key, new Music("resources_/music_/grim reaper.ogg"));
                        break;
                    case "highscore":
                        musicMap.put(key, new Music("resources_/music_/highscore.ogg"));
                        break;
                    case "purity":
                        musicMap.put(key, new Music("resources_/music_/purity.ogg"));
                        break;
                    case "yellow_orange":
                        musicMap.put(key, new Music("resources_/music_/yellow orange.ogg"));
                        break;
                    case "surface":
                        musicMap.put(key, new Music("resources_/music_/surface.ogg"));
                        break;
                    case "nokia_arabic":
                        musicMap.put(key, new Music("resources_/music_/nokia arabic.ogg"));
                        break;
                    case "nrg_drink":
                        musicMap.put(key, new Music("resources_/music_/nrg drink.ogg"));
                        break;
                    case "happy":
                        musicMap.put(key, new Music("resources_/music_/happy.ogg"));
                        break;
                    case "moonsugar":
                        musicMap.put(key, new Music("resources_/music_/moonsugar.ogg"));
                        break;
                    case "rubik":
                        musicMap.put(key, new Music("resources_/music_/rubik.ogg"));
                        break;
                    case "ignition":
                        musicMap.put(key, new Music("resources_/music_/ignition.ogg"));
                        break;
                    case "the_force":
                        musicMap.put(key, new Music("resources_/music_/the force.ogg"));
                        break;
                    case "candyland":
                        musicMap.put(key, new Music("resources_/music_/candyland.ogg"));
                        break;
                    case "infectious":
                        musicMap.put(key, new Music("resources_/music_/infectious.ogg"));
                        break;
                    case "crazy":
                        musicMap.put(key, new Music("resources_/music_/crazy.ogg"));
                        break;
                    case "race":
                        musicMap.put(key, new Music("resources_/music_/race.ogg"));
                        break;
                    case "ice_flow":
                        musicMap.put(key, new Music("resources_/music_/ice flow.ogg"));
                        break;
                    case "kalimba":
                        musicMap.put(key, new Music("resources_/music_/kalimba 119.70.ogg"));
                        break;
                    case "party":
                        musicMap.put(key, new Music("resources_/music_/party.ogg"));
                        break;
                    case "bass":
                        musicMap.put(key, new Music("resources_/music_/bass.ogg"));
                        break;
                    case "hazmat":
                        musicMap.put(key, new Music("resources_/music_/hazmat.ogg"));
                        break;
                    case "panda_dance":
                        musicMap.put(key, new Music("resources_/music_/panda dance.ogg"));
                        break;
                    case "get_up":
                        musicMap.put(key, new Music("resources_/music_/get up.ogg"));
                        break;
                    case "river":
                        musicMap.put(key, new Music("resources_/music_/river.ogg"));
                        break;
                    case "follow":
                        musicMap.put(key, new Music("resources_/music_/follow.ogg"));
                        break;
                    case "slime":
                        musicMap.put(key, new Music("resources_/music_/slime.ogg"));
                        break;
                    case "euphoria":
                        musicMap.put(key, new Music("resources_/music_/euphoria.ogg"));
                        break;
                    case "nautilus":
                        musicMap.put(key, new Music("resources_/music_/nautilus.ogg"));
                        break;
                    case "liaquo":
                        musicMap.put(key, new Music("resources_/music_/lia quo.ogg"));
                        break;
                    case "crazy_frog":
                        musicMap.put(key, new Music("resources_/music_/crazy frog.ogg"));
                        break;
                    case "never_lose_hope":
                        musicMap.put(key, new Music("resources_/music_/never lose hope.ogg"));
                        break;
                    case "skystrike":
                        musicMap.put(key, new Music("resources_/music_/skystrike.ogg"));
                        break;
                    case "starchaser":
                        musicMap.put(key, new Music("resources_/music_/starchaser.ogg"));
                        break;
                    case "a_newer_dawn":
                        musicMap.put(key, new Music("resources_/music_/a newer dawn.ogg"));
                        break;
                    case "monody":
                        musicMap.put(key, new Music("resources_/music_/monody.ogg"));
                        break;
                    case "unity":
                        musicMap.put(key, new Music("resources_/music_/unity.ogg"));
                        break;
                    case "xenogenesis":
                        musicMap.put(key, new Music("resources_/music_/xenogenesis.ogg"));
                        break;
                    case "time_stops":
                        musicMap.put(key, new Music("resources_/music_/time stops.ogg"));
                        break;
                    case "badland":
                        musicMap.put(key, new Music("resources_/music_/badland.ogg"));
                        break;
                    case "challenger":
                        musicMap.put(key, new Music("resources_/music_/challenger.ogg"));
                        break;
                    case "bluemoon":
                        musicMap.put(key, new Music("resources_/music_/bluemoon 112.50.ogg"));
                        break;
                    case "cherry_blossoms":
                        musicMap.put(key, new Music("resources_/music_/cherry blossoms.ogg"));
                        break;
                    case "walkman":
                        musicMap.put(key, new Music("resources_/music_/walkman.ogg"));
                        break;
                    case "beyond_the_walls":
                        musicMap.put(key, new Music("resources_/music_/beyond the walls.ogg"));
                        break;
                    case "april":
                        musicMap.put(key, new Music("resources_/music_/april.ogg"));
                        break;
                    case "sunlight":
                        musicMap.put(key, new Music("resources_/music_/sunlight.ogg"));
                        break;
                    case "cyptik_dance":
                        musicMap.put(key, new Music("resources_/music_/cryptik dance.ogg"));
                        break;
                    case "leaving_leafwood_forest":
                        musicMap.put(key, new Music("resources_/music_/leaving leafwood forest.ogg"));
                        break;
                    case "troglodyte":
                        musicMap.put(key, new Music("resources_/music_/troglodyte.ogg"));
                        break;
                    case "mayo":
                        musicMap.put(key, new Music("resources_/music_/mayo.ogg"));
                        break;
                    case "starship_showdown":
                        musicMap.put(key, new Music("resources_/music_/starship showdown.ogg"));
                        break;
                    case "kumquat":
                        musicMap.put(key, new Music("resources_/music_/kumquat.ogg"));
                        break;
                    case "jaclyn":
                        musicMap.put(key, new Music("resources_/music_/jaclyn.ogg"));
                        break;
                    case "jacques":
                        musicMap.put(key, new Music("resources_/music_/jacques.ogg"));
                        break;
                    case "liftoff":
                        musicMap.put(key, new Music("resources_/music_/liftoff.ogg"));
                        break;
                    case "never_be_alone":
                        musicMap.put(key, new Music("resources_/music_/never be alone.ogg"));
                        break;
                    case "solitude":
                        musicMap.put(key, new Music("resources_/music_/solitude.ogg"));
                        break;
                    case "close_to_the_sun":
                        musicMap.put(key, new Music("resources_/music_/close to the sun.ogg"));
                        break;
                    case "nanamori":
                        musicMap.put(key, new Music("resources_/music_/nanamori.ogg"));
                        break;
                    case "fury":
                        musicMap.put(key, new Music("resources_/music_/fury.ogg"));
                        break;
                    case "desu_ka":
                        musicMap.put(key, new Music("resources_/music_/desu ka.ogg"));
                        break;
                    case "voices":
                        musicMap.put(key, new Music("resources_/music_/voices.ogg"));
                        break;
                    case "dancing":
                        musicMap.put(key, new Music("resources_/music_/dancing.ogg"));
                        break;
                    case "shining_space":
                        musicMap.put(key, new Music("resources_/music_/shining space.ogg"));
                        break;
                    case "space_invaders":
                        musicMap.put(key, new Music("resources_/music_/space invaders.ogg"));
                        break;
                    case "drippy_dub":
                        musicMap.put(key, new Music("resources_/music_/drippy dub.ogg"));
                        break;
                    case "fake_princess":
                        musicMap.put(key, new Music("resources_/music_/fake princess.ogg"));
                        break;
                    case "lazergun":
                        musicMap.put(key, new Music("resources_/music_/lazergun.ogg"));
                        break;
                    case "afterimage":
                        musicMap.put(key, new Music("resources_/music_/afterimage.ogg"));
                        break;
                    case "star_wars_remix":
                        musicMap.put(key, new Music("resources_/music_/star wars remix.ogg"));
                        break;
                    case "kiss_me_kiss_you":
                        musicMap.put(key, new Music("resources_/music_/kiss me kiss you.ogg"));
                        break;
                    case "enigma":
                        musicMap.put(key, new Music("resources_/music_/enigma.ogg"));
                        break;
                    case "never_make_it":
                        musicMap.put(key, new Music("resources_/music_/never make it.ogg"));
                        break;
                    case "flight":
                        musicMap.put(key, new Music("resources_/music_/flight.ogg"));
                        break;
                    case "journey":
                        musicMap.put(key, new Music("resources_/music_/journey.ogg"));
                        break;
                    case "lonley_forest":
                        musicMap.put(key, new Music("resources_/music_/lonley forest.ogg"));
                        break;
                    case "pirate":
                        musicMap.put(key, new Music("resources_/music_/pirate.ogg"));
                        break;
                    case "aquamarine":
                        musicMap.put(key, new Music("resources_/music_/aquamarine.ogg"));
                        break;
                    case "boombox":
                        musicMap.put(key, new Music("resources_/music_/boombox.ogg"));
                        break;
                    case "niconico_dreams":
                        musicMap.put(key, new Music("resources_/music_/niconico dreams.ogg"));
                        break;
                    case "requiem_dream":
                        musicMap.put(key, new Music("resources_/music_/requiem dream.ogg"));
                        break;
                    case "eurodancer":
                        musicMap.put(key, new Music("resources_/music_/eurodancer.ogg"));
                        break;
                    case "hello":
                        musicMap.put(key, new Music("resources_/music_/hello.ogg"));
                        break;
                    case "mayday":
                        musicMap.put(key, new Music("resources_/music_/mayday.ogg"));
                        break;
                    case "fireflies_remix":
                        musicMap.put(key, new Music("resources_/music_/fireflies remix.ogg"));
                        break;
                    case "echolands":
                        musicMap.put(key, new Music("resources_/music_/echolands.ogg"));
                        break;
                    case "voiceless":
                        musicMap.put(key, new Music("resources_/music_/voiceless.ogg"));
                        break;
                    case "vaporwave_aesthetics":
                        musicMap.put(key, new Music("resources_/music_/vaporwave aesthetics.ogg"));
                        break;
                    case "happy_troll":
                        musicMap.put(key, new Music("resources_/music_/happy troll.ogg"));
                        break;
                    case "dimension":
                        musicMap.put(key, new Music("resources_/music_/dimension.ogg"));
                        break;
                    case "crystal_caves":
                        musicMap.put(key, new Music("resources_/music_/crystal caves.ogg"));
                        break;
                    case "elevate":
                        musicMap.put(key, new Music("resources_/music_/elevate.ogg"));
                        break;
                    case "okiba":
                        musicMap.put(key, new Music("resources_/music_/okiba.ogg"));
                        break;
                    case "falling_mysts":
                        musicMap.put(key, new Music("resources_/music_/falling mysts.ogg"));
                        break;
                    case "newgrounds_return":
                        musicMap.put(key, new Music("resources_/music_/newgrounds return.ogg"));
                        break;
                    case "body_jammer":
                        musicMap.put(key, new Music("resources_/music_/body jammer.ogg"));
                        break;
                    case "flirt":
                        musicMap.put(key, new Music("resources_/music_/flirt.ogg"));
                        break;
                    case "retry":
                        musicMap.put(key, new Music("resources_/music_/retry.ogg"));
                        break;
                    case "jet_set":
                        musicMap.put(key, new Music("resources_/music_/jet set.ogg"));
                        break;
                    case "the_calling":
                        musicMap.put(key, new Music("resources_/music_/the calling.ogg"));
                        break;
                    case "tria":
                        musicMap.put(key, new Music("resources_/music_/tria.ogg"));
                        break;
                    case "endgame":
                        musicMap.put(key, new Music("resources_/music_/endgame.ogg"));
                        break;
                    case "night_out":
                        musicMap.put(key, new Music("resources_/music_/night out.ogg"));
                        break;
                    case "april_showers":
                        musicMap.put(key, new Music("resources_/music_/april showers.ogg"));
                        break;
                    case "bathtub":
                        musicMap.put(key, new Music("resources_/music_/bathtub.ogg"));
                        break;
                    case "laszlo":
                        musicMap.put(key, new Music("resources_/music_/laszlo.ogg"));
                        break;
                    case "force":
                        musicMap.put(key, new Music("resources_/music_/force.ogg"));
                        break;
                    case "spectre":
                        musicMap.put(key, new Music("resources_/music_/spectre.ogg"));
                        break;
                    case "fade":
                        musicMap.put(key, new Music("resources_/music_/fade.ogg"));
                        break;
                    case "bangarang":
                        musicMap.put(key, new Music("resources_/music_/bangarang.ogg"));
                        break;
                    case "bun_dem":
                        musicMap.put(key, new Music("resources_/music_/bun dem.ogg"));
                        break;
                    case "sleepyhead":
                        musicMap.put(key, new Music("resources_/music_/sleepyhead.ogg"));
                        break;
                    case "dance_till_you're_dead":
                        musicMap.put(key, new Music("resources_/music_/dance till you're dead.ogg"));
                        break;
                    case "animals":
                        musicMap.put(key, new Music("resources_/music_/animals.ogg"));
                        break;
                    case "yeah":
                        musicMap.put(key, new Music("resources_/music_/yeah.ogg"));
                        break;
                    case "november":
                        musicMap.put(key, new Music("resources_/music_/november.ogg"));
                        break;
                    case "florescence":
                        musicMap.put(key, new Music("resources_/music_/florescence.ogg"));
                        break;
                    case "mantis_shrimp_showdown":
                        musicMap.put(key, new Music("resources_/music_/mantis shrimp showdown.ogg"));
                        break;
                    case "holy_war":
                        musicMap.put(key, new Music("resources_/music_/holy war.ogg"));
                        break;
                    case "massacre":
                        musicMap.put(key, new Music("resources_/music_/massacre.ogg"));
                        break;
                    case "believe":
                        musicMap.put(key, new Music("resources_/music_/believe.ogg"));
                        break;
                    case "karma":
                        musicMap.put(key, new Music("resources_/music_/karma.ogg"));
                        break;
                    case "refraction":
                        musicMap.put(key, new Music("resources_/music_/refraction.ogg"));
                        break;
                    case "glome":
                        musicMap.put(key, new Music("resources_/music_/glome.ogg"));
                        break;
                    case "red_roses":
                        musicMap.put(key, new Music("resources_/music_/red roses.ogg"));
                        break;
                    case "a_little_older":
                        musicMap.put(key, new Music("resources_/music_/a little older.ogg"));
                        break;
                    case "when_time_tears":
                        musicMap.put(key, new Music("resources_/music_/when time tears.ogg"));
                        break;
                    case "hold_on_to_me":
                        musicMap.put(key, new Music("resources_/music_/hold on to me.ogg"));
                        break;
                    case "i'm_still_alive":
                        musicMap.put(key, new Music("resources_/music_/i'm still alive.ogg"));
                        break;
                    case "nirmiti":
                        musicMap.put(key, new Music("resources_/music_/nirmiti.ogg"));
                        break;
                    case "avast_your_ass":
                        musicMap.put(key, new Music("resources_/music_/avast your ass.ogg"));
                        break;
                    case "soulwind":
                        musicMap.put(key, new Music("resources_/music_/soulwind.ogg"));
                        break;
                    case "zenith":
                        musicMap.put(key, new Music("resources_/music_/zenith.ogg"));
                        break;
                    case "portals":
                        musicMap.put(key, new Music("resources_/music_/portals.ogg"));
                        break;
                    case "mellow":
                        musicMap.put(key, new Music("resources_/music_/mellow.ogg"));
                        break;
                    case "lullaby_remix":
                        musicMap.put(key, new Music("resources_/music_/lullaby remix.ogg"));
                        break;
                    case "florescentia":
                        musicMap.put(key, new Music("resources_/music_/florescentia.ogg"));
                        break;
                    case "bioluminescent":
                        musicMap.put(key, new Music("resources_/music_/bioluminescent.ogg"));
                        break;
                    case "let's_bounce":
                        musicMap.put(key, new Music("resources_/music_/let's bounce.ogg"));
                        break;
                    case "orbit":
                        musicMap.put(key, new Music("resources_/music_/orbit.ogg"));
                        break;
                    case "luminous":
                        musicMap.put(key, new Music("resources_/music_/luminous.ogg"));
                        break;
                    case "mako":
                        musicMap.put(key, new Music("resources_/music_/mako.ogg"));
                        break;
                    case "love_talk":
                        musicMap.put(key, new Music("resources_/music_/love talk.ogg"));
                        break;
                    case "trouble":
                        musicMap.put(key, new Music("resources_/music_/trouble.ogg"));
                        break;
                    case "peepee":
                        musicMap.put(key, new Music("resources_/music_/peepee.ogg"));
                        break;
                    case "rose":
                        musicMap.put(key, new Music("resources_/music_/rose.ogg"));
                        break;
                    case "fateful_mist":
                        musicMap.put(key, new Music("resources_/music_/fateful mist.ogg"));
                        break;
                    case "flashyizz_crazy":
                        musicMap.put(key, new Music("resources_/music_/flashyizz crazy.ogg"));
                        break;
                    case "glorious_morning":
                        musicMap.put(key, new Music("resources_/music_/glorious morning.ogg"));
                        break;
                    case "flyboy_and_gabbergirl":
                        musicMap.put(key, new Music("resources_/music_/flyboy and gabbergirl.ogg"));
                        break;
                    case "battletown":
                        musicMap.put(key, new Music("resources_/music_/battletown.ogg"));
                        break;
                    case "nanobyte":
                        musicMap.put(key, new Music("resources_/music_/nanobyte.ogg"));
                        break;
                    case "fantasy":
                        musicMap.put(key, new Music("resources_/music_/fantasy.ogg"));
                        break;
                    case "artificial":
                        musicMap.put(key, new Music("resources_/music_/artificial.ogg"));
                        break;
                    case "run_away":
                        musicMap.put(key, new Music("resources_/music_/run away.ogg"));
                        break;
                    case "dream":
                        musicMap.put(key, new Music("resources_/music_/dream.ogg"));
                        break;
                    case "drained":
                        musicMap.put(key, new Music("resources_/music_/drained.ogg"));
                        break;
                    case "just_kidding":
                        musicMap.put(key, new Music("resources_/music_/just kidding.ogg"));
                        break;
                    case "saunter":
                        musicMap.put(key, new Music("resources_/music_/saunter.ogg"));
                        break;
                    case "help_me_up":
                        musicMap.put(key, new Music("resources_/music_/help me up.ogg"));
                        break;
                    case "ocean_of_stars":
                        musicMap.put(key, new Music("resources_/music_/ocean of stars.ogg"));
                        break;
                    case "bash":
                        musicMap.put(key, new Music("resources_/music_/bash.ogg"));
                        break;
                    case "whirlwind":
                        musicMap.put(key, new Music("resources_/music_/whirlwind.ogg"));
                        break;
                    case "screamroom":
                        musicMap.put(key, new Music("resources_/music_/screamroom.ogg"));
                        break;
                    case "ichor":
                        musicMap.put(key, new Music("resources_/music_/ichor.ogg"));
                        break;
                    case "time_lapse":
                        musicMap.put(key, new Music("resources_/music_/time lapse.ogg"));
                        break;
                    case "calm":
                        musicMap.put(key, new Music("resources_/music_/calm.ogg"));
                        break;
                    case "gloomy":
                        musicMap.put(key, new Music("resources_/music_/gloomy.ogg"));
                        break;
                    case "love's_song":
                        musicMap.put(key, new Music("resources_/music_/love's song.ogg"));
                        break;
                    case "blast_em":
                        musicMap.put(key, new Music("resources_/music_/blast em.ogg"));
                        break;
                    case "lost_in_the_rhythm":
                        musicMap.put(key, new Music("resources_/music_/lost in the rhythm.ogg"));
                        break;
                    case "zelda_palace":
                        musicMap.put(key, new Music("resources_/music_/zelda palace.ogg"));
                        break;
                    case "form":
                        musicMap.put(key, new Music("resources_/music_/form.ogg"));
                        break;
                    case "paladin":
                        musicMap.put(key, new Music("resources_/music_/paladin.ogg"));
                        break;
                    case "myriad":
                        musicMap.put(key, new Music("resources_/music_/myriad.ogg"));
                        break;
                    case "pyrolysis":
                        musicMap.put(key, new Music("resources_/music_/pyrolysis.ogg"));
                        break;
                    case "elevatia":
                        musicMap.put(key, new Music("resources_/music_/elevatia.ogg"));
                        break;
                    case "prelude_remix":
                        musicMap.put(key, new Music("resources_/music_/prelude remix.ogg"));
                        break;
                    case "could_it_be":
                        musicMap.put(key, new Music("resources_/music_/could it be.ogg"));
                        break;
                    case "spooky":
                        musicMap.put(key, new Music("resources_/music_/spooky.ogg"));
                        break;
                    case "ena":
                        musicMap.put(key, new Music("resources_/music_/ena.ogg"));
                        break;
                    case "reapers":
                        musicMap.put(key, new Music("resources_/music_/reapers.ogg"));
                        break;
                    case "last_dance":
                        musicMap.put(key, new Music("resources_/music_/last dance.ogg"));
                        break;
                    case "reminisce":
                        musicMap.put(key, new Music("resources_/music_/reminisce.ogg"));
                        break;
                    case "who_you_are":
                        musicMap.put(key, new Music("resources_/music_/who you are.ogg"));
                        break;
                    case "stardrive":
                        musicMap.put(key, new Music("resources_/music_/stardrive.ogg"));
                        break;
                    case "level_one":
                        musicMap.put(key, new Music("resources_/music_/level one.ogg"));
                        break;
                    case "lights":
                        musicMap.put(key, new Music("resources_/music_/lights.ogg"));
                        break;
                    case "skybound":
                        musicMap.put(key, new Music("resources_/music_/skybound.ogg"));
                        break;
                    case "open_your_eyes":
                        musicMap.put(key, new Music("resources_/music_/open your eyes.ogg"));
                        break;
                    case "party_hard_remix":
                        musicMap.put(key, new Music("resources_/music_/party hard remix.ogg"));
                        break;
                    case "nice_vibes":
                        musicMap.put(key, new Music("resources_/music_/nice vibes.ogg"));
                        break;
                    case "horizons_remix":
                        musicMap.put(key, new Music("resources_/music_/horizons remix.ogg"));
                        break;
                    case "ablixa":
                        musicMap.put(key, new Music("resources_/music_/ablixa.ogg"));
                        break;
                    case "untitled":
                        musicMap.put(key, new Music("resources_/music_/untitled.ogg"));
                        break;
                    case "litoff":
                        musicMap.put(key, new Music("resources_/music_/litoff.ogg"));
                        break;
                    case "nuetronium":
                        musicMap.put(key, new Music("resources_/music_/nuetronium.ogg"));
                        break;
                    case "sky_venture":
                        musicMap.put(key, new Music("resources_/music_/sky venture.ogg"));
                        break;
                    case "sad_machine_remix":
                        musicMap.put(key, new Music("resources_/music_/sad machine remix.ogg"));
                        break;
                    case "jazz_jackrabbit_remix":
                        musicMap.put(key, new Music("resources_/music_/jazz jackrabbit remix.ogg"));
                        break;
                    case "shadow_queen_part_2_remix":
                        musicMap.put(key, new Music("resources_/music_/shadow queen part 2 remix.ogg"));
                        break;
                    case "let's_stomp":
                        musicMap.put(key, new Music("resources_/music_/let's stomp.ogg"));
                        break;
                    case "twinrova":
                        musicMap.put(key, new Music("resources_/music_/twinrova.ogg"));
                        break;
                    case "our_home":
                        musicMap.put(key, new Music("resources_/music_/our home.ogg"));
                        break;
                    case "geometry_dance":
                        musicMap.put(key, new Music("resources_/music_/geometry dance.ogg"));
                        break;
                    case "this_is_geometry_dash":
                        musicMap.put(key, new Music("resources_/music_/this is geometry dash.ogg"));
                        break;
                    case "waves":
                        musicMap.put(key, new Music("resources_/music_/waves.ogg"));
                        break;
                    case "among_us_trap":
                        musicMap.put(key, new Music("resources_/music_/among us trap.ogg"));
                        break;
                    case "legend":
                        musicMap.put(key, new Music("resources_/music_/legend.ogg"));
                        break;
                    case "this_girl":
                        musicMap.put(key, new Music("resources_/music_/this girl.ogg"));
                        break;
                    case "sunwalker":
                        musicMap.put(key, new Music("resources_/music_/sunwalker.ogg"));
                        break;
                    case "afterglow":
                        musicMap.put(key, new Music("resources_/music_/afterglow.ogg"));
                        break;
                    case "a_cow_that_is_actually_a_chicken":
                        musicMap.put(key, new Music("resources_/music_/a cow that is actually a chicken.ogg"));
                        break;
                    case "ストーカー":
                        musicMap.put(key, new Music("resources_/music_/ストーカー.ogg"));
                        break;

                    // template
                    /*case "null":
                        musicMap.put(key, new Music("resources_/music_/null.ogg"));
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
            currentMusic = key;
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
        return musicMap.get(key);
    }

}
