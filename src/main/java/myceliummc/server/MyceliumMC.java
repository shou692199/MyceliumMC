package MyceliumMC.server;

import MyceliumMC.server.threads.RealtimeThread;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spigotmc.AsyncCatcher;

public class MyceliumMC {
    public static final Logger log = LogManager.getLogger("MyceliumMC");
    private static final String version = "2.1.0";
    private static final String native_version = "v1_15_R0";

    private static MyceliumMCConfig config = new MyceliumMCConfig("MyceliumMC.yml");

    private static final RealtimeThread realtimeThread = new RealtimeThread();

    public static String getVersion(){
        return version;
    }

    public static String getNativeVersion() {
        return native_version;
    }

    public static void onServerStart() {
        realtimeThread.start();
    }

    public static boolean asyncCatch(String reason) {
        if (AsyncCatcher.enabled && Thread.currentThread() != MinecraftServer.getServerInst().primaryThread) {
            log.debug("Try to asynchronously " + reason + ", caught!", new RuntimeException());
            return true;
        }
        return false;
    }

    public static boolean asyncCatch(String reason, Runnable runnable) {
        if (asyncCatch(reason)) {
            postPrimaryThread(runnable);
            return true;
        }
        return false;
    }

    public static MyceliumMCConfig getConfig() {
        return config;
    }

    public static void postPrimaryThread(Runnable runnable) {
        MinecraftServer.getServerInst().addScheduledTask(runnable);
    }

    public static int getCurrentTick() {
        return getConfig().enableRealtime ? RealtimeThread.currentTick : MinecraftServer.currentTick;
    }

    public static void forceSaveWorlds() {
        log.info("Force save worlds:");
        boolean oldAsyncCatcher = AsyncCatcher.enabled;
        AsyncCatcher.enabled = false;

        try {
            log.info("Force saving players..");
            MinecraftServer.getServerInst().getPlayerList().saveAllPlayerData();

            log.info("Force saving chunks..");
            for (WorldServer worldServer : MinecraftServer.getServerInst().worldServerList) {
                try {
                    worldServer.saveAllChunks(true, null);
                    worldServer.flushToDisk();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AsyncCatcher.enabled = oldAsyncCatcher;
        log.info("Force save complete!");
    }
}
