package MyceliumMC.server.remapper;

import net.md_5.specialsource.JarMapping;
import net.md_5.specialsource.JarRemapper;

public class MyceliumMCRemapper extends JarRemapper {
    public MyceliumMCRemapper(JarMapping jarMapping) {
        super(jarMapping);
    }

    public String mapSignature(String signature, boolean typeSignature) {
        try {
            return super.mapSignature(signature, typeSignature);
        } catch (Exception e) {
            return signature;
        }
    }

    @Override
    public String mapFieldName(String owner, String name, String desc, int access) {
        return super.mapFieldName(owner, name, desc, -1);
    }
}
