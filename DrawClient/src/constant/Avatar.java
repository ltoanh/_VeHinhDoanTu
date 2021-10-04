package constant;

/**
 *
 * @author whiwf
 */
public class Avatar {

    public static final String ASSETS_PATH = "src/client/view/assets/";
    public static final String PATH = "src/client/view/assets/avatar/";
    public static final String PATH_RESOURCE = "/client/view/assets/avatar/";

    public static final String[] LIST = {
        "icons8-brave-96.png",
        "icons8-futurama-fry-96.png",
        "icons8-lumpy-space-princess-96.png",
        "icons8-morty-smith-96.png",
        "icons8-ninja-turtle-96.png",
        "icons8_animation_96px.png",
        "icons8_avatar_96px.png",
        "icons8_bad_piggies_96px.png",
        "icons8_bendy_96px.png",
        "icons8_captain_america_96px.png",
        "icons8_circled_user_female_skin_type_7_96px.png",
        "icons8_circled_user_male_skin_type_7_96px.png",
        "icons8_crash_bandicoot_96px.png",
        "icons8_deadpool_96px.png",
        "icons8_elektrovieh_96px.png",
        "icons8_gandalf_96px.png",
        "icons8_groot_96px.png",
        "icons8_hinata_96px.png",
        "icons8_hulk_96px.png",
        "icons8_ignore_96px.png",
        "icons8_iron_man_96px.png",
        "icons8_jake_96px.png",
        "icons8_jetpack_joyride_96px.png",
        "icons8_joker_dc_96px.png",
        "icons8_logan_x_men_96px.png",
        "icons8_magneto_96px.png",
        "icons8_minecraft_main_character_96px.png",
        "icons8_minecraft_skeleton_96px.png",
        "icons8_minecraft_zombie_96px.png",
        "icons8_morpheus_96px.png",
        "icons8_naruto_96px.png",
        "icons8_neo_96px.png",
        "icons8_orc_96px.png",
        "icons8_pelican_96px.png",
        "icons8_pennywise_96px.png",
        "icons8_pikachu_pokemon_96px.png",
        "icons8_rick_sanchez_96px.png",
        "icons8_sailor_moon_96px.png",
        "icons8_saitama_96px.png",
        "icons8_sasuke_uchiha_96px.png",
        "icons8_sonic_the_hedgehog_96px.png",
        "icons8_spider-man_head_96px.png",
        "icons8_steven_universe_96px.png",
        "icons8_super_mario_96px.png",
        "icons8_the_flash_head_96px.png",
        "icons8_transformer_96px.png",
        "icons8_trinity_96px.png",
        "icons8_vietnam_96px.png",
        "icons8_year_of_rooster_96px.png"
    };
    
    public static String getAvatarFilNameFromPath(String path) {
        String[] splitted = path.split("/");
        return splitted[splitted.length - 1];
    }
}
