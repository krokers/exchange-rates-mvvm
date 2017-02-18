package eu.rampsoftware.er.widget;

import android.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.rampsoftware.er.R;
import eu.rampsoftware.er.data.SingleValue;

public class LineChartExtensions {

    private static SimpleDateFormat mDateFormatter = new SimpleDateFormat("dd/MM", Locale.getDefault());

    @BindingAdapter({"bind:items"})
    public static void populateDiagram(LineChart view, List<SingleValue> items) {

        if (null == items || items.size() == 0) {
            return;
        }
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            final SingleValue item = items.get(i);
            final Entry entry = new Entry(i, (float) item.getValue(), item);
            entries.add(entry);
        }
        LineDataSet dataSet = new LineDataSet(entries, view.getContext().getString(R.string.currency_value));
        LineData lineData = new LineData(dataSet);

        formatXAxisLabels(view, items);
        view.setData(lineData);
        view.invalidate();
    }

    private static void formatXAxisLabels(final LineChart view, final List<SingleValue> items) {
        final XAxis xAxis = view.getXAxis();
        xAxis.setValueFormatter((value, axis) -> {
            int index = (int) value;
            if (index >= items.size()) {
                return "";
            }
            return mDateFormatter.format(items.get(index).getDate());
        });
    }
}
