package warcraft.components.error.tests;

import warcraft.components.error.RouteError;
import warcraft.components.tests.AbstractRouteTests;

public class RouteTestsError extends AbstractRouteTests {

	@Override
	public void before() {
		route=new RouteError();
	}

}
