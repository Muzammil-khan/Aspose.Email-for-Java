package com.aspose.email.examples.appointment;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.aspose.email.Appointment;
import com.aspose.email.AppointmentAction;
import com.aspose.email.CalendarWriter;
import com.aspose.email.IcsSaveOptions;
import com.aspose.email.MailAddress;
import com.aspose.email.MailAddressCollection;
import com.aspose.email.ParticipationStatus;

public class WorkingWithAppointments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Create Appointment
		createAppointment();
		
		//Load Appointment
		loadAppointment();
		
		SetParticipantStatusOfAppointmentAttendees();
		
		System.out.println("Done..");
	}

	public static void createAppointment()
	{
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(2011, 11, 10, 10, 12, 11);
		Date startDate = c.getTime();
		c.set(2012, 10, 13, 13, 11, 12);
		Date endDate = c.getTime();
		
		String description = "Eum an graeco equidem intellegam. Harum omnium forensibus his ne.";
		
		MailAddress organizer = new MailAddress("aaa@amail.com", "Organizer");
		
		MailAddressCollection attendees = new MailAddressCollection();
		MailAddress attendee1 = new MailAddress("bbb@bmail.com", "First attendee");
		MailAddress attendee2 = new MailAddress("ccc@cmail.com", "Second attendee");
		attendee1.setParticipationStatus(ParticipationStatus.Accepted);
		attendee2.setParticipationStatus(ParticipationStatus.Declined);
		attendees.addMailAddress(attendee1);
		attendees.addMailAddress(attendee2);
		
		// Create and initialize an instance of the Appointment class
        Appointment appointment = new Appointment(
            "Meeting Room 3 at Office Headquarters",	// Location
            "Monthly Meeting",                      	// Summary
            description,    							// Description
            startDate,     								// Start date
            endDate,     								// End date
            organizer,                      			// Organizer
            attendees);                					// Attendees

        appointment.setCreatedDate(c.getTime());		// Created Date
        appointment.setLastModifiedDate(c.getTime());	//Last Modified Date
        appointment.save("Appointment.ics");
		
	}
	
	public static void loadAppointment()
	{
		Appointment loadedAppointment = Appointment.load("Appointment.ics");
		System.out.println("Loaded Appointment details are as follows:");
		// Display the appointment information on screen
		System.out.println("Summary: " + loadedAppointment.getSummary());
		System.out.println("Location: " + loadedAppointment.getLocation());
		System.out.println("Description: " + loadedAppointment.getDescription());
		System.out.println("Start date: " + loadedAppointment.getStartDate());
		System.out.println("End date: " + loadedAppointment.getEndDate());
		System.out.println("Organizer: " + loadedAppointment.getOrganizer());
		System.out.println("Attendees: " + loadedAppointment.getAttendees());
		System.out.println("Created Date: " + loadedAppointment.getCreatedDate());
		System.out.println("Last Modified Date: " + loadedAppointment.getLastModifiedDate());
		System.out.println("Appointment loaded successfully!");
	}
	
	public static void SetParticipantStatusOfAppointmentAttendees()
	{
		//ExStart: SetParticipantStatusOfAppointmentAttendees
		String location = "Room 5";
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(2011, 11, 10, 10, 12, 11);
		Date startDate = c.getTime();
		c.set(2012, 10, 13, 13, 11, 12);
		Date endDate = c.getTime();
		MailAddress organizer = new MailAddress("aaa@amail.com", "Organizer");
		MailAddressCollection attendees = new MailAddressCollection();
		MailAddress attendee1 = new MailAddress("bbb@bmail.com", "First attendee");
		MailAddress attendee2 = new MailAddress("ccc@cmail.com", "Second attendee");

		attendee1.setParticipationStatus(ParticipationStatus.Accepted);
		attendee2.setParticipationStatus(ParticipationStatus.Declined);
		attendees.addMailAddress(attendee1);
		attendees.addMailAddress(attendee2);

		Appointment target = new Appointment(location, startDate, endDate, organizer, attendees);
		//ExEnd: SetParticipantStatusOfAppointmentAttendees
	}
	
	public static void WriteMultipleEventsToICS()
	{
		//ExStart: WriteMultipleEventsToICS
		IcsSaveOptions saveOptions = new IcsSaveOptions();
		saveOptions.setAction(AppointmentAction.Create);
		CalendarWriter writer = new CalendarWriter("WirteMultipleEventsToICS_out.ics", saveOptions);
		
		//Set the start and end date of meeting
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.set(2018, Calendar.JUNE, 19, 19, 0, 0); //19 Jan, 2015 - 1900
		Date startDate = calendar.getTime();
		calendar.set(2018, Calendar.JUNE, 19, 20, 0, 0);
		Date endDate = calendar.getTime();
		
		MailAddressCollection attendees = new MailAddressCollection();
		attendees.addItem(new MailAddress("recepientEmail@gmail.com"));
		
		try {
		    for (int i = 0; i < 10; i++) {
		    	//create Appointment instance
				Appointment app = new Appointment("Room 112", //location
						startDate, //start time
						endDate, //end time
						new MailAddress("organizer@domain.com"), //organizer
						attendees //attendee
				);
		        app.setDescription("Test body " + i);
		        app.setSummary("Test summary:" + i);
		        writer.write(app);
		    }
		} finally {
		    writer.dispose();
		}
		//ExEnd: WriteMultipleEventsToICS
	}
}
