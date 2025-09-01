package gay.beegirl.entity;

public interface StaminaHolder {
    default float getStaminaHideDelay() { return 1; }
    int getStamina();
    int getMaxStamina();
    void setStamina(int stamina);
}
