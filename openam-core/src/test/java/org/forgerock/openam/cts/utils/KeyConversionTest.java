/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2013-2015 ForgeRock AS.
 */

package org.forgerock.openam.cts.utils;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.*;
import com.iplanet.dpro.session.SessionID;
import com.iplanet.dpro.session.SessionIDExtensions;
import org.testng.annotations.Test;

public class KeyConversionTest {
    @Test
    public void shouldDecodeEncodedKey() {
        // Given
        KeyConversion conversion = new KeyConversion();
        String key = "badger";

        // When
        String result = conversion.decodeKey(conversion.encodeKey(key));

        // Then
        assertEquals(result, key);
    }

    @Test
    public void shouldNotFailToEncryptKey() {
        // Given
        SessionID key = mock(SessionID.class);
        SessionIDExtensions extensions = mock(SessionIDExtensions.class);
        given(key.getExtension()).willReturn(extensions);
        given(extensions.getStorageKey()).willReturn("badger");

        KeyConversion conversion = new KeyConversion();

        // When
        String result = conversion.encryptKey(key);

        // Then
        assertNotNull(result);
    }
}
