package com.asgeirr.grupoasesorestest.data.preferences;

import java.util.ArrayList;
import java.util.List;

public interface PreferencesHelper {
    void detach();

    void saveQuery(String query);

    ArrayList<String> getQueriesSaved();
}
