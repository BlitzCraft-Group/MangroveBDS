package cn.nukkit.network.protocol;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * author: MagicDroidX &amp; iNevet
 * Nukkit Project
 */
public interface ProtocolInfo {

    /**
     * Actual Minecraft: PE protocol version
     */
    @SuppressWarnings("UnnecessaryBoxing")
    int CURRENT_PROTOCOL = Integer.valueOf("388"); // DO NOT REMOVE BOXING

    List<Integer> SUPPORTED_PROTOCOLS = Ints.asList(CURRENT_PROTOCOL);

    String MINECRAFT_VERSION = "v1.13.0";
    String MINECRAFT_VERSION_NETWORK = "1.13.0";

    byte LOGIN_PACKET = 0x01;
    byte PLAY_STATUS_PACKET = 0x02;
    byte SERVER_TO_CLIENT_HANDSHAKE_PACKET = 0x03;
    byte CLIENT_TO_SERVER_HANDSHAKE_PACKET = 0x04;
    byte DISCONNECT_PACKET = 0x05;
    byte RESOURCE_PACKS_INFO_PACKET = 0x06;
    byte RESOURCE_PACK_STACK_PACKET = 0x07;
    byte RESOURCE_PACK_CLIENT_RESPONSE_PACKET = 0x08;
    byte TEXT_PACKET = 0x09;
    byte SET_TIME_PACKET = 0x0a;
    byte START_GAME_PACKET = 0x0b;
    byte ADD_PLAYER_PACKET = 0x0c;
    byte ADD_ENTITY_PACKET = 0x0d;
    byte REMOVE_ENTITY_PACKET = 0x0e;
    byte ADD_ITEM_ENTITY_PACKET = 0x0f;
    byte TAKE_ITEM_ENTITY_PACKET = 0x11;
    byte MOVE_ENTITY_ABSOLUTE_PACKET = 0x12;
    byte MOVE_PLAYER_PACKET = 0x13;
    byte RIDER_JUMP_PACKET = 0x14;
    byte UPDATE_BLOCK_PACKET = 0x15;
    byte ADD_PAINTING_PACKET = 0x16;
    byte TICK_SYNC_PACKET = 0x17;
    byte LEVEL_SOUND_EVENT_PACKET_V1 = 0x18;
    byte LEVEL_EVENT_PACKET = 0x19;
    byte BLOCK_EVENT_PACKET = 0x1a;
    byte ENTITY_EVENT_PACKET = 0x1b;
    byte MOB_EFFECT_PACKET = 0x1c;
    byte UPDATE_ATTRIBUTES_PACKET = 0x1d;
    byte INVENTORY_TRANSACTION_PACKET = 0x1e;
    byte MOB_EQUIPMENT_PACKET = 0x1f;
    byte MOB_ARMOR_EQUIPMENT_PACKET = 0x20;
    byte INTERACT_PACKET = 0x21;
    byte BLOCK_PICK_REQUEST_PACKET = 0x22;
    byte ENTITY_PICK_REQUEST_PACKET = 0x23;
    byte PLAYER_ACTION_PACKET = 0x24;
    byte ENTITY_FALL_PACKET = 0x25;
    byte HURT_ARMOR_PACKET = 0x26;
    byte SET_ENTITY_DATA_PACKET = 0x27;
    byte SET_ENTITY_MOTION_PACKET = 0x28;
    byte SET_ENTITY_LINK_PACKET = 0x29;
    byte SET_HEALTH_PACKET = 0x2a;
    byte SET_SPAWN_POSITION_PACKET = 0x2b;
    byte ANIMATE_PACKET = 0x2c;
    byte RESPAWN_PACKET = 0x2d;
    byte CONTAINER_OPEN_PACKET = 0x2e;
    byte CONTAINER_CLOSE_PACKET = 0x2f;
    byte PLAYER_HOTBAR_PACKET = 0x30;
    byte INVENTORY_CONTENT_PACKET = 0x31;
    byte INVENTORY_SLOT_PACKET = 0x32;
    byte CONTAINER_SET_DATA_PACKET = 0x33;
    byte CRAFTING_DATA_PACKET = 0x34;
    byte CRAFTING_EVENT_PACKET = 0x35;
    byte GUI_DATA_PICK_ITEM_PACKET = 0x36;
    byte ADVENTURE_SETTINGS_PACKET = 0x37;
    byte BLOCK_ENTITY_DATA_PACKET = 0x38;
    byte PLAYER_INPUT_PACKET = 0x39;
    byte FULL_CHUNK_DATA_PACKET = 0x3a;
    byte SET_COMMANDS_ENABLED_PACKET = 0x3b;
    byte SET_DIFFICULTY_PACKET = 0x3c;
    byte CHANGE_DIMENSION_PACKET = 0x3d;
    byte SET_PLAYER_GAME_TYPE_PACKET = 0x3e;
    byte PLAYER_LIST_PACKET = 0x3f;
    byte SIMPLE_EVENT_PACKET = 0x40;
    byte EVENT_PACKET = 0x41;
    byte SPAWN_EXPERIENCE_ORB_PACKET = 0x42;
    byte CLIENTBOUND_MAP_ITEM_DATA_PACKET = 0x43;
    byte MAP_INFO_REQUEST_PACKET = 0x44;
    byte REQUEST_CHUNK_RADIUS_PACKET = 0x45;
    byte CHUNK_RADIUS_UPDATED_PACKET = 0x46;
    byte ITEM_FRAME_DROP_ITEM_PACKET = 0x47;
    byte GAME_RULES_CHANGED_PACKET = 0x48;
    byte CAMERA_PACKET = 0x49;
    byte BOSS_EVENT_PACKET = 0x4a;
    byte SHOW_CREDITS_PACKET = 0x4b;
    byte AVAILABLE_COMMANDS_PACKET = 0x4c;
    byte COMMAND_REQUEST_PACKET = 0x4d;
    byte COMMAND_BLOCK_UPDATE_PACKET = 0x4e;
    byte COMMAND_OUTPUT_PACKET = 0x4f;
    byte UPDATE_TRADE_PACKET = 0x50;
    byte UPDATE_EQUIPMENT_PACKET = 0x51;
    byte RESOURCE_PACK_DATA_INFO_PACKET = 0x52;
    byte RESOURCE_PACK_CHUNK_DATA_PACKET = 0x53;
    byte RESOURCE_PACK_CHUNK_REQUEST_PACKET = 0x54;
    byte TRANSFER_PACKET = 0x55;
    byte PLAY_SOUND_PACKET = 0x56;
    byte STOP_SOUND_PACKET = 0x57;
    byte SET_TITLE_PACKET = 0x58;
    byte ADD_BEHAVIOR_TREE_PACKET = 0x59;
    byte STRUCTURE_BLOCK_UPDATE_PACKET = 0x5a;
    byte SHOW_STORE_OFFER_PACKET = 0x5b;
    byte PURCHASE_RECEIPT_PACKET = 0x5c;
    byte PLAYER_SKIN_PACKET = 0x5d;
    byte SUB_CLIENT_LOGIN_PACKET = 0x5e;
    byte INITIATE_WEB_SOCKET_CONNECTION_PACKET = 0x5f;
    byte SET_LAST_HURT_BY_PACKET = 0x60;
    byte BOOK_EDIT_PACKET = 0x61;
    byte NPC_REQUEST_PACKET = 0x62;
    byte PHOTO_TRANSFER_PACKET = 0x63;
    byte MODAL_FORM_REQUEST_PACKET = 0x64;
    byte MODAL_FORM_RESPONSE_PACKET = 0x65;
    byte SERVER_SETTINGS_REQUEST_PACKET = 0x66;
    byte SERVER_SETTINGS_RESPONSE_PACKET = 0x67;
    byte SHOW_PROFILE_PACKET = 0x68;
    byte SET_DEFAULT_GAME_TYPE_PACKET = 0x69;
    byte MOVE_ENTITY_DELTA_PACKET = 0x6f;
    byte SET_SCOREBOARD_IDENTITY_PACKET = 0x70;
    byte SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET = 0x71;
    byte UPDATE_SOFT_ENUM_PACKET = 0x72;
    byte NETWORK_STACK_LATENCY_PACKET = 0x73;

    byte SCRIPT_CUSTOM_EVENT_PACKET = 0x75;
    byte SPAWN_PARTICLE_EFFECT_PACKET = 0x76;
    byte AVAILABLE_ENTITY_IDENTIFIERS_PACKET = 0x77;
    byte LEVEL_SOUND_EVENT_PACKET_V2 = 0x78;
    byte NETWORK_CHUNK_PUBLISHER_UPDATE_PACKET = 0x79;
    byte BIOME_DEFINITION_LIST_PACKET = 0x7a;
    byte LEVEL_SOUND_EVENT_PACKET = 0x7b;
    byte LEVEL_EVENT_GENERIC_PACKET = 0x7c;
    byte LECTERN_UPDATE_PACKET = 0x7d;
    byte VIDEO_STREAM_CONNECT_PACKET = 0x7e;
    //byte ADD_ENTITY_PACKET = 0x7f;
    //byte REMOVE_ENTITY_PACKET = 0x80;
    byte CLIENT_CACHE_STATUS_PACKET = (byte) 0x81;
    byte ON_SCREEN_TEXTURE_ANIMATION_PACKET = (byte) 0x82;
    byte MAP_CREATE_LOCKED_COPY_PACKET = (byte) 0x83;
    byte STRUCTURE_TEMPLATE_DATA_EXPORT_REQUEST = (byte) 0x84;
    byte STRUCTURE_TEMPLATE_DATA_EXPORT_RESPONSE = (byte) 0x85;
    byte UPDATE_BLOCK_PROPERTIES = (byte) 0x86;
    byte CLIENT_CACHE_BLOB_STATUS_PACKET = (byte) 0x87;
    byte CLIENT_CACHE_MISS_RESPONSE_PACKET = (byte) 0x88;
    byte EDUCATION_SETTINGS_PACKET = (byte) 0x89;
    byte EMOTE_PACKET = (byte) 0x8a;
    byte MULTIPLAYER_SETTINGS_PACKET = (byte) 0x8b;
    byte SETTINGS_COMMAND_PACKET = (byte) 0x8c;
    byte ANVIL_DAMAGE_PACKET = (byte) 0x8d;
    byte COMPLETED_USING_ITEM_PACKET = (byte) 0x8e;
    byte NETWORK_SETTINGS_PACKET = (byte) 0x8f;
    byte PLAYER_AUTH_INPUT_PACKET = (byte) 0x90;

    byte BATCH_PACKET = (byte) 0xff;
}
