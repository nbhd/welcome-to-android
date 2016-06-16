
package net.boondockradio.grepos.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Repositories {

    @SerializedName("total_count")
    public int totalCount;
    @SerializedName("incomplete_results")
    public boolean incompleteResults;
    @SerializedName("items")
    public List<Repository> items = new ArrayList<Repository>();

}
