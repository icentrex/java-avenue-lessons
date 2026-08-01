package lesson9.hw;

import java.util.Objects;

public class RemoteControl {

    private String chargingType;
    private int buttonsNumber;

    public RemoteControl(String chargingType, int buttonsNumber) {
        this.chargingType = chargingType;
        this.buttonsNumber = buttonsNumber;
    }

    public String getChargingType() {
        return chargingType;
    }

    public int getButtonsNumber() {
        return buttonsNumber;
    }

    public void setChargingType(String chargingType) {
        this.chargingType = chargingType;
    }

    public void setButtonsNumber(int buttonsNumber) {
        this.buttonsNumber = buttonsNumber;
    }

    @Override
    public String toString() {
        return "RemoteControl{" +
                "typeOfRecharge='" + chargingType + '\'' +
                ", buttonsNumber=" + buttonsNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RemoteControl that = (RemoteControl) o;
        return buttonsNumber == that.buttonsNumber && Objects.equals(chargingType, that.chargingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargingType, buttonsNumber);
    }
}
