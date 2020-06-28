package MyceliumMC.server.inventory;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyceliumForgeItemCap implements Cloneable {
    protected NBTTagCompound capNBT;

    public MyceliumForgeItemCap(NBTTagCompound capNBT) {
        this.capNBT = capNBT;
    }

    public NBTTagCompound getItemCap() {
        return capNBT.copy();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyceliumForgeItemCap)) {
            return false;
        }

        return this.capNBT.equals(((MyceliumForgeItemCap) obj).capNBT);
    }

    @Override
    public int hashCode() {
        return this.capNBT.hashCode();
    }

    @Override
    public MyceliumForgeItemCap clone() {
        try {
            return (MyceliumForgeItemCap) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public static void setItemCap(net.minecraft.item.ItemStack nmsItemStack, ItemStack bukkitItemStack) {
        if (nmsItemStack != null && nmsItemStack.capabilities != null) {
            NBTTagCompound capNBT = nmsItemStack.capabilities.serializeNBT();
            if (capNBT != null && !capNBT.hasNoTags()) {
                bukkitItemStack.setForgeItemCap(new MyceliumForgeItemCap(capNBT));
            }
        }
    }

    public String serializeNBT() {
        try {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            CompressedStreamTools.writeCompressed(capNBT, buf);
            return Base64.encodeBase64String(buf.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(MyceliumForgeItemCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static MyceliumForgeItemCap deserializeNBT(String serializedNBT) {
        if (serializedNBT != null) {
            ByteArrayInputStream buf = new ByteArrayInputStream(Base64.decodeBase64(serializedNBT));
            try {
                NBTTagCompound capNBT = CompressedStreamTools.readCompressed(buf);
                return new MyceliumForgeItemCap(capNBT);
            } catch (IOException ex) {
                Logger.getLogger(MyceliumForgeItemCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
