package gay.beegirl.skyislands.util;

public interface LivingEntityAccess {
    //void islands$setFreeFalling(boolean isFreeFalling);
    boolean islands$isFreeFalling();
    int islands$getFreeFallTicks();
    int islands$getDiveTicks();
    int islands$getGlideTicks();
}
