package com.spark.ana.service;

import reader.OnTagReport;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.*;


public class TagReportListenerImplementation implements OnTagReport {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private Set<String> tagList = ConcurrentHashMap.newKeySet();
    private Set<String> tagList = new LinkedHashSet();

    @Override
    public void onReport(String data) {
//  public void onTagReported(ImpinjReader reader, TagReport report) {
        //hfv @5Oct2023
        tagList.clear();
//        List<Tag> tags = report.getTags();
        System.out.print(" data: " + data + ".");

            tagList.add(data);

    }


    public Set<String> getTagList() {
        //hfv @19jul truqueado para probar
        String dummyEpc1 = "3035E1967C06F54000000152";
        String dummyEpc2 = "3035E1967C06F280000001E4";
        String dummyEpc3 = "3035E1967C0802400000009E";
        tagList.add(dummyEpc1);
        tagList.add(dummyEpc2);
        tagList.add(dummyEpc3);
////////////////////////////////////////////////////////

        return tagList;
    }

    public void setTagList(Set<String> tagList) {
        this.tagList = tagList;
    }
}
