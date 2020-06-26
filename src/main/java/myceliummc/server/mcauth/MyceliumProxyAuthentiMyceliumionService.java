package MyceliumMC.server.mcauth;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import java.net.Proxy;

public class MyceliumProxyAuthenticationService extends YggdrasilAuthenticationService {
    public MyceliumProxyAuthenticationService(Proxy proxy, String clientToken) {
        super(proxy, clientToken);
    }

    @Override
    public MinecraftSessionService createMinecraftSessionService() {
        return new MyceliumProxyMinecraftSessionService(this);
    }
}
