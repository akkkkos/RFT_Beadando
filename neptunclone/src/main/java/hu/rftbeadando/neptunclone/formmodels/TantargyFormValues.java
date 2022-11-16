package hu.rftbeadando.neptunclone.formmodels;

public class TantargyFormValues {
    private String dayOfTheWeek;
    private int durationInMinutes;
    private int kredit;
    private int maxHallgato;
    private String name;
    private String startTime;

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public int getMaxHallgato() {
        return maxHallgato;
    }

    public void setMaxHallgato(int maxHallgato) {
        this.maxHallgato = maxHallgato;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
