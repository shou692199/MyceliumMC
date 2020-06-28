package MyceliumMC.server.patcher;

public interface IPatcher {
    byte[] transform(String className, byte[] basicClass);
}
