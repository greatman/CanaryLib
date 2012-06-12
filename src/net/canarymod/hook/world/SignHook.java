package net.canarymod.hook.world;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.blocks.SignBlock;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;

/**
 * Sign hook. Contains infomation about a sign either being changed by or shown to a player
 * @author Jason Jones
 */
public class SignHook extends CancelableHook{
    
    private SignBlock sign;
    private Player player;
    
    public SignHook(Player player, SignBlock sign, boolean change){
        this.player = player;
        this.sign = sign;
        this.type = change ? Hook.Type.SIGN_CHANGE : Hook.Type.SIGN_SHOW;
    }
    
    /**
     * Gets the player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the sign
     * @return sign
     */
    public SignBlock getSign(){
        return sign;
    }
    
    /**
     * Return the set of Data in this order: PLAYER SIGN ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, sign, isCancelled() };
    }

}