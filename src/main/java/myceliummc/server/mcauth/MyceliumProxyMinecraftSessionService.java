package MyceliumMC.server.mcauth;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;

public class MyceliumProxyMinecraftSessionService extends YggdrasilMinecraftSessionService {
    protected MyceliumProxyMinecraftSessionService(MyceliumProxyAuthenticationService authenticationService) {
        super(authenticationService);
    }

    @Override
    protected GameProfile fillGameProfile(GameProfile profile, boolean requireSecure) {
        return profile;
    }

}
