package com.epam.tc.hw5.cucumber.steps;

import com.epam.tc.hw5.pages.AuthorizedHomePage;
import com.epam.tc.hw5.pages.DifferentElementsPage;
import com.epam.tc.hw5.pages.UnauthorizedHomePage;
import com.epam.tc.hw5.pages.UserTablePage;

public class AbstractStep {
    protected UnauthorizedHomePage unauthorizedHomePage;
    protected AuthorizedHomePage authorizedHomePage;
    protected DifferentElementsPage differentElementsPage;
    protected UserTablePage userTablePage;

    public AbstractStep() {
    }
}
