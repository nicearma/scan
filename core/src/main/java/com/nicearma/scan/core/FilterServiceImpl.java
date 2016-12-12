package com.nicearma.scan.core;


import java.util.HashSet;

public class FilterServiceImpl implements FilterService {

    public static HashSet<String> alreadyScaned= new HashSet<>();


    @Override
    public String filter(String name) {
        return null;
    }

    @Override
    public boolean isFiltered(String name) {
        if(alreadyScaned.contains(name)){
            return true;
        }
        alreadyScaned.add(name);
        return false;
    }

}
