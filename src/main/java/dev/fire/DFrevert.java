package dev.fire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.fire.config.Config;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DFrevert implements ModInitializer {
	public static final String MOD_NAME = "DF Revert";
	public static final String MOD_ID = "dfrevert";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public void onInitialize() {
		DFrevert.LOGGER.info("so long, and thanks for all the fish!");
		Config.getConfig();
	}
}