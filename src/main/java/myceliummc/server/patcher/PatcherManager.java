package MyceliumMC.server.patcher;

import MyceliumMC.server.patcher.plugin.CoreProtectPatcher;
import MyceliumMC.server.patcher.plugin.DisablePluginPatcher;
import MyceliumMC.server.patcher.plugin.DynmapPacher;

import java.util.HashMap;
import java.util.Map;

public class PatcherManager {
    private static Map<String, IPatcher> pluginPatcher = new HashMap<>();

    static {
        registerPluginPatcher("dynmap", new DynmapPacher());
        registerPluginPatcher("CoreProtect", new CoreProtectPatcher());
    }

    public static IPatcher getPluginPatcher(String pluginName) {
        return pluginPatcher.get(pluginName);
    }

    public static boolean registerPluginPatcher(String pluginName, IPatcher patcher) {
        if (!pluginPatcher.containsKey(pluginName) && patcher != null) {
            pluginPatcher.put(pluginName, patcher);
            return true;
        }
        return false;
    }
}
