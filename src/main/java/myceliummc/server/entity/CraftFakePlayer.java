package MyceliumMC.server.entity;

import MyceliumMC.server.MyceliumMC;
import net.minecraftforge.common.util.FakePlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.permissions.Permission;

public class CraftFakePlayer extends CraftPlayer {

    public CraftFakePlayer(CraftServer server, FakePlayer entity) {
        super(server, entity);
    }

    @Override
    public boolean hasPermission(String name) {
        if (MyceliumMC.getConfig().fakePlayerPermissions.contains(name)) {
            return true;
        }
        return super.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        if (MyceliumMC.getConfig().fakePlayerPermissions.contains(perm.getName())) {
            return true;
        }
        return super.hasPermission(perm);
    }

    @Override
    public boolean isPermissionSet(String name) {
        if (MyceliumMC.getConfig().fakePlayerPermissions.contains(name)) {
            return true;
        }
        return super.hasPermission(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        if (MyceliumMC.getConfig().fakePlayerPermissions.contains(perm.getName())) {
            return true;
        }
        return super.hasPermission(perm);
    }
}
