package org.roger600.lienzo.client.resources;

import org.gwtproject.core.client.GWT;
import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.ClientBundleWithLookup;
import org.gwtproject.resources.client.DataResource;

public interface LienzoExamplesResources extends ClientBundleWithLookup
{

    //public static final LienzoExamplesResources INSTANCE = GWT.create(LienzoTestsResources.class);

    @Source( "images/yamaha_logo_red.jpg" )
    DataResource yamahaLogoJPG();

    @Source( "images/yamaha_logo_trans.png" )
    DataResource yamahaLogoPNG();

    @Source( "images/envelope.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource envelopeIconSVG();

    @Source( "images/envelope_nogrid.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource envelopeNoGridIconSVG();

    @Source( "images/event-end.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource eventEndIconSVG();

    @Source( "images/event-end-nogrid.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource eventEndNoGridIconSVG();

    @Source( "images/rectangle.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource rectangleSVG();

    @Source( "images/task-user.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskUserSVG();

    @Source( "images/task-script.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskScript();

    @Source( "images/task-business-rule.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskBusinessRule();

    @Source( "images/cancel.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource cancel();

    @Source( "images/circle.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource circle();

    @Source( "images/clock-o.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource clockO();

    @Source( "images/event-end.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource eventEnd();

    @Source( "images/event-intermediate.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource eventIntermediate();

    @Source( "images/event-start.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource eventStart();

    @Source( "images/lane.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource lane();

    @Source( "images/parallel-event.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource parallelEvent();

    @Source( "images/parallel_multiple.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource parallelMultiple();

    @Source( "images/plus-square.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource plusQuare();

    @Source( "images/sub-process.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource subProcess();

    @Source( "images/p.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource p();

    /** Composite SVGs **/

    @Source( "images/svgcomp/taskComposite.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskComposite();

    @Source( "images/svgcomp/taskScriptComposite.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskScriptComposite();

    @Source( "images/svgcomp/taskUserComposite.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource taskUserComposite();



    @Source( "images/edit.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource edit();

    @Source( "images/gears.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource gears();

    @Source( "images/delete.svg" )
    @DataResource.MimeType("image/svg+xml")
    DataResource delete();

}

