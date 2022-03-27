import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("9장. 단위 테스트")
public class Chapter9 {

    /**
     * 깨끗한 테스트 코드 - 수정 전
     */
    public void testGetPageHieratchyAsXml() throws Exception {
        crawler.addPage(root, PathParser.parse("PageOne"));
        crawler.addPage(root, PathParser.parse("PageOne.ChildOne"));
        crawler.addPage(root, PathParser.parse("PageTwo"));

        request.setResource("root");
        request.addInput("type", "pages");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(
            new FitNesseContext(root),
            request
        );
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("<name>PageOne</name>", xml);
        assertSubString("<name>PageTwo</name>", xml);
        assertSubString("<name>ChildOne</name>", xml);
    }

    public void testGetPageHieratchyAsXmlDoesntContainSymbolicLinks() throws Exception {
        WikiPage pageOne = crawler.addPage(root, PathParser.parse("PageOne"));
        crawler.addPage(root, PathParser.parse("PageOne.ChildOne"));
        crawler.addPage(root, PathParser.parse("PageTwo"));

        PageData data = pageOne.getData();
        WikiPageProperties properties = data.getProperties();
        WikiPageProperty symLinks = properties.set(SymbolicPage.PROPERTY_NAME);
        symLinks.set("SymPage", "PageTwo");
        pageOne.commit(data);

        request.setResource("root");
        request.addInput("type", "pages");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(
            new FitNesseContext(root),
            request
        );
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("<name>PageOne</name>", xml);
        assertSubString("<name>PageTwo</name>", xml);
        assertSubString("<name>ChildOne</name>", xml);
        assertNotSubString("SymPage", xml);
    }

    public void testGetDataAsHtml() throws Exception {
        crawler.addPage(root, PathParser.parse("TestPageOne"), "test page");

        request.setResource("TestPageOne");
        request.addInput("type", "data");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(
            new FitNesseContext(root),
            request
        );
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("test page", xml);
        assertSubString("<Test", xml);
    }

    /**
     * 깨끗한 테스트 코드 - 수정 후
     */
    public void testGetPageHierarchyAsXml() throws Exception {
        makePages("PageOne", "PageOne.ChildOne", "PageTwo");

        submitRequest("root", "type:pages");

        assertResponseIsXML();
        assertResponseContains(
            "<name>PageOne</name>",
            "<name>PageTwo</name>",
            "<name>ChildOne</name>"
        );
    }

    public void testSymbolicLinksAreNotInXmlPageHierarchy() chrows Exception {
        WikiPage page = makePage("PageOne");
        makePages("PageOne.ChildOne", "PageTwo");

        addLinkTo(page, "PageTwo", "SymPage");

        submitRequest("root", "type:pages");

        assertResponseIsXML();
        assertResponseContains(
            "<name>PageOne</name>",
            "<name>PageTwo</name>",
            "<name>ChildOne</name>"
        );
        assertResponseDoesNotContain("SymPage");
    }

    public void testGetDataAsXml() throws Exception {
        makePageWithContent("TestPageOne", "test page");

        submitRequest("TestPageOne", "type:data");

        assertResponseIsXML();
        assertResponseContains("test page", "<Test");
    }

    /**
     * 깨끗한 테스트 코드 - 이중표준 - 수정 전
     */
    @Test
    public void turnOnLoTempAlarmAtThreashold() throws Exception {
        hw.setTemp(WAY_TOO_COLD);
        controller.tic();
        assertTrue(hw.heaterState());
        assertTrue(hw.blowerState());
        assertFalse(hw.coolerState());
        assertFalse(hw.hiTempAlarm());
        assertTrue(hw.loTempAlarm());
    }

    /**
     * 깨끗한 테스트 코드 - 이중표준 - 수정 후
     */
    @Test
    public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
        tooHot();
        assertEquals("hBChl", hw.getState());
    }

    @Test
    public void turnOnHeaterAndBlowerIfTooCold() throws Exception {
        tooCold();
        assertEquals("HBchl", hw.getState());
    }

    @Test
    public void turnOnHiTempAlarmAtThreshold() throws Exception {
        wayTooHot();
        assertEquals("hBCHl", hw.getState());
    }

    @Test
    public void turnOnLoTempAlarmAtThreshold() throws Exception {
        wayTooCold();
        assertEquals("HBchL", hw.getState());
    }

    /**
     * 테스트 당 assert 하나
     */
    public void testGetPageHierarchyAsXml ( ) throws Exception {
        givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

        whenRequestIsIssued("root", "type:pages");

        thenResponseShouldBeXML( );
    }

    public void testGetPageHierachyHasRightTags( ) throws Exception {
        givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

        whenRequestIsIssued("root", "type:pages");

        thenResponseShouldContain(
            "<name>PageOne</name>", "<name>PageTwo</name>","<name>ChildOne</name>"
        );
    }

    /**
     *  테스트 당 assert 하나 - 테스트 당 개념 하나
     */
    public void testAddMonths() {
        SerialDate dl = SerialDate.createlnstance(31, 5, 2004);

        SerialDate d2 = SerialDate.addMonths(1, dl);
        assertEquals(30, d2.getDayDfMonth());
        assertEquals(6, d2.getMonth());
        assertEquals(2004, d2.getYYYY());

        SerialDate d3 = SerialDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth());
        assertEquals(2004, d3.getYYYY());

        SerialDate d4 = SerialDate.addMonths(1, SerialDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7,  d4.getMonth()) ;
        assertEquals(2004, d4.getYYYY());
    }
}
