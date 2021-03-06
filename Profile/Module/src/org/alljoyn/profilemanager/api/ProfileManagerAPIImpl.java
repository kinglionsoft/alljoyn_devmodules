/******************************************************************************
* Copyright (c) 2013, AllSeen Alliance. All rights reserved.
*
*    Permission to use, copy, modify, and/or distribute this software for any
*    purpose with or without fee is hereby granted, provided that the above
*    copyright notice and this permission notice appear in all copies.
*
*    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
*    WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
*    MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
*    ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
*    WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
*    ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
*    OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
******************************************************************************/
package org.alljoyn.profilemanager.api;

import org.alljoyn.bus.BusObject;
import org.alljoyn.bus.SignalEmitter;
import org.alljoyn.bus.annotation.BusMethod;
import org.alljoyn.devmodules.interfaces.ModuleAPIInterface;

public class ProfileManagerAPIImpl implements ModuleAPIInterface {
	//callback interface so that specific callback can be invoked
	//Signal emitter - so we have multiple apps that receive callback data
	
	class ProfileManagerCallbackObject implements ProfileManagerCallbackInterface, BusObject {
		public void onProfileFound(String peer) {	}

		public void onProfileLost(String peer) {	}

		public void CallbackJSON(int transactionId, String module,
				String jsonCallbackData) {
			
		}

		public void CallbackData(int transactionId, String module,
				byte[] rawData, int totalParts, int partNumber) {
			
		}
	}
	
	private ProfileManagerCallbackObject profileCallbackObject = new ProfileManagerCallbackObject();
	public static ProfileManagerCallbackInterface profileCallback; //look into possibly just folding this in with the regular imple so I don't have to static var this and link it with the ChatImpl
	
	public ProfileManagerAPIImpl() {
	}
	
	@Override
	public void connectCallbackObject(int sessionId, String joiner) {
		SignalEmitter emitter = new SignalEmitter(profileCallbackObject, sessionId, SignalEmitter.GlobalBroadcast.Off);
		profileCallback = emitter.getInterface(ProfileManagerCallbackInterface.class);
	}

	@Override
	public BusObject getBusObject() {
		return new ProfileManagerAPIObject();
	}

	@Override
	public String getBusObjectPath() {
		return ProfileManagerAPIObject.OBJECT_PATH;
	}

	@Override
	public BusObject getCallbackBusObject() {
		// TODO Auto-generated method stub
		return profileCallbackObject;
	}

	@Override
	public String getCallbackBusObjectPath() {
		// TODO Auto-generated method stub
		return profileCallbackObject.OBJECT_PATH;
	}
	
}
