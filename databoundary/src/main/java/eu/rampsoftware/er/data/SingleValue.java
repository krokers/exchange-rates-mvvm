package eu.rampsoftware.er.data;


import java.util.Date;

public class SingleValue {
    private Date mDate;
    private double mValue;
    public SingleValue(final Date date, final double value) {
        mDate = date;
        mValue = value;
    }

    public Date getDate() {
        return mDate;
    }

    public double getValue() {
        return mValue;
    }
}
