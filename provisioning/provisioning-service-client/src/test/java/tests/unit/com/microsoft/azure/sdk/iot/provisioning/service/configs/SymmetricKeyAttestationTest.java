// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package tests.unit.com.microsoft.azure.sdk.iot.provisioning.service.configs;

import com.microsoft.azure.sdk.iot.deps.util.Base64;
import com.microsoft.azure.sdk.iot.provisioning.service.configs.SymmetricKeyAttestation;
import mockit.Deencapsulation;
import org.junit.Test;

import static org.junit.Assert.*;

public class SymmetricKeyAttestationTest
{
    private static final String PRIMARY_KEY_TEXT = "validPrimaryKey";
    private static final String SECONDARY_KEY_TEXT = "validSecondaryKey";
    private static final String EMPTY_KEY = "";

    private static final String VALID_PRIMARY_KEY = Base64.encodeBase64StringLocal(PRIMARY_KEY_TEXT.getBytes());
    private static final String VALID_SECONDARY_KEY = Base64.encodeBase64StringLocal(SECONDARY_KEY_TEXT.getBytes());

    /* SRS_SYMMETRIC_KEY_ATTESTATION_001: [The constructor shall throw IllegalArgumentException if the provided primary key is null or empty.] */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullPrimaryKey()
    {
        // arrange
        // act
        new SymmetricKeyAttestation(null, VALID_SECONDARY_KEY);

        // assert
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_001: [The constructor shall throw IllegalArgumentException if the provided primary key is null or empty.] */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnEmptyPrimaryKey()
    {
        // arrange
        // act
        new SymmetricKeyAttestation(EMPTY_KEY, VALID_SECONDARY_KEY);

        // assert
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_003: [The constructor shall throw IllegalArgumentException if the provided primary key is null or empty.] */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullSecondaryKey()
    {
        // arrange
        // act
        new SymmetricKeyAttestation(VALID_PRIMARY_KEY, null);

        // assert
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_003: [The constructor shall throw IllegalArgumentException if the provided primary key is null or empty.] */
    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnEmptySecondaryKey()
    {
        // arrange
        // act
        new SymmetricKeyAttestation(VALID_PRIMARY_KEY, EMPTY_KEY);

        // assert
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_004: [The constructor shall store the provided secondary key.] */
    /* SRS_SYMMETRIC_KEY_ATTESTATION_002: [The constructor shall store the provided primary key.] */
    @Test
    public void constructorStoresPrimarySecondaryKeys()
    {
        // arrange
        // act
        SymmetricKeyAttestation symmetricKeyAttestation = new SymmetricKeyAttestation(VALID_PRIMARY_KEY, VALID_SECONDARY_KEY);

        // assert
        assertEquals(VALID_PRIMARY_KEY, Deencapsulation.getField(symmetricKeyAttestation, "primaryKey"));
        assertEquals(VALID_SECONDARY_KEY, Deencapsulation.getField(symmetricKeyAttestation, "secondaryKey"));
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_005: [The constructor shall throw IllegalArgumentException if the provided symmetricKey is null.] */
    @Test(expected = IllegalArgumentException.class)
    public void constructorCopyThrowsOnNullSymmetricKeyAttestation()
    {
        // arrange
        // act
        new SymmetricKeyAttestation((SymmetricKeyAttestation) null);
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_006: [The constructor shall store the primaryKey and secondaryKey provided in the symmetricKey.] */
    @Test
    public void constructorCopyStorePrimarySecondaryKeys()
    {
        // arrange
        SymmetricKeyAttestation symmetricKeyAttestation = new SymmetricKeyAttestation(VALID_PRIMARY_KEY, VALID_SECONDARY_KEY);

        // act
        SymmetricKeyAttestation symmetricKeyAttestationCopy = new SymmetricKeyAttestation(symmetricKeyAttestation);

        // assert
        assertEquals(VALID_PRIMARY_KEY, Deencapsulation.getField(symmetricKeyAttestationCopy, "primaryKey"));
        assertEquals(VALID_SECONDARY_KEY, Deencapsulation.getField(symmetricKeyAttestationCopy, "secondaryKey"));
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_007: [The getPrimaryKey shall return the stored primary key.] */
    /* SRS_SYMMETRIC_KEY_ATTESTATION_008: [The getSecondaryKey shall return the stored secondary key.] */
    @Test
    public void getterSucceeds()
    {
        // arrange
        SymmetricKeyAttestation symmetricKeyAttestation = new SymmetricKeyAttestation(VALID_PRIMARY_KEY, VALID_SECONDARY_KEY);

        // act
        // assert
        assertEquals(VALID_PRIMARY_KEY, symmetricKeyAttestation.getPrimaryKey());
        assertEquals(VALID_SECONDARY_KEY, symmetricKeyAttestation.getSecondaryKey());
    }

    /* SRS_SYMMETRIC_KEY_ATTESTATION_009: [The SymmetricKeyAttestation shall provide an empty constructor to make GSON happy.] */
    @Test
    public void emptyConstructorSucceed()
    {
        // arrange
        // act
        SymmetricKeyAttestation symmetricKeyAttestation = Deencapsulation.newInstance(SymmetricKeyAttestation.class);

        // assert
        assertNotNull(symmetricKeyAttestation);
    }

}
