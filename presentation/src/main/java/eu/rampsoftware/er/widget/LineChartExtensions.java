package eu.rampsoftware.er.widget;

import android.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import eu.rampsoftware.er.data.SingleValue;

/**
 * Created by Ramps on 2017-02-18.
 */

public class LineChartExtensions {

    @BindingAdapter({"bind:items"})
    public static void populateDiagram(LineChart view, List<SingleValue> items) {
        if (null == items || items.size() == 0) {
            return;
        }
        List<Entry> entries = new ArrayList<>();
        for (int i=0; i<items.size(); i++) {
            final SingleValue item = items.get(i);
            entries.add(new Entry(i, (float) item.getValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        view.setData(lineData);
        view.invalidate();
    }
}
