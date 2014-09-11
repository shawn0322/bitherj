/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bither.bitherj.test.crypto;

import junit.framework.TestCase;

import net.bither.bitherj.crypto.bip38.Bip38;
import net.bither.bitherj.exception.AddressFormatException;
import net.bither.bitherj.test.ApplicationTest;
import net.bither.bitherj.utils.Utils;

import java.io.UnsupportedEncodingException;

public class Bip38Test extends ApplicationTest {


    public void testEncryptNoCompression() throws InterruptedException, AddressFormatException {
        String encoded = Bip38.encryptNoEcMultiply("TestingOneTwoThree",
                "5KN7MzqK5wt2TP1fQCYyHBtDrXdJuXbUzm4A9rKAteGu3Qi5CVR");
        assertEquals(encoded, "6PRVWUbkzzsbcVac2qwfssoUJAN1Xhrg6bNk8J7Nzm5H7kxEbn2Nh2ZoGg");
        assertTrue(Bip38.isBip38PrivateKey(encoded));
    }


    public void testDecryptNoCompression() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PRVWUbkzzsbcVac2qwfssoUJAN1Xhrg6bNk8J7Nzm5H7kxEbn2Nh2ZoGg",
                "TestingOneTwoThree");
        assertEquals(decoded, "5KN7MzqK5wt2TP1fQCYyHBtDrXdJuXbUzm4A9rKAteGu3Qi5CVR");
    }


    public void testDecryptNoCompressionWithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PRVWUbkzzsbcVac2qwfssoUJAN1Xhrg6bNk8J7Nzm5H7kxEbn2Nh2ZoGg",
                "TestingOneTwoThree");
        assertEquals(decoded, "5KN7MzqK5wt2TP1fQCYyHBtDrXdJuXbUzm4A9rKAteGu3Qi5CVR");
    }


    public void testEncryptCompression1() throws InterruptedException, AddressFormatException {
        String encoded = Bip38.encryptNoEcMultiply("TestingOneTwoThree",
                "L44B5gGEpqEDRS9vVPz7QT35jcBG2r3CZwSwQ4fCewXAhAhqGVpP");
        assertEquals(encoded, "6PYNKZ1EAgYgmQfmNVamxyXVWHzK5s6DGhwP4J5o44cvXdoY7sRzhtpUeo");
        assertTrue(Bip38.isBip38PrivateKey(encoded));
    }


    public void testDecryptCompression1() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PYNKZ1EAgYgmQfmNVamxyXVWHzK5s6DGhwP4J5o44cvXdoY7sRzhtpUeo",
                "TestingOneTwoThree");
        assertEquals(decoded, "L44B5gGEpqEDRS9vVPz7QT35jcBG2r3CZwSwQ4fCewXAhAhqGVpP");
    }


    public void testDecryptCompression1WithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PYNKZ1EAgYgmQfmNVamxyXVWHzK5s6DGhwP4J5o44cvXdoY7sRzhtpUeo",
                "TestingOneTwoThree");
        assertEquals(decoded, "L44B5gGEpqEDRS9vVPz7QT35jcBG2r3CZwSwQ4fCewXAhAhqGVpP");
    }


    public void testEncryptCompression2() throws InterruptedException, AddressFormatException {
        String encoded = Bip38.encryptNoEcMultiply("Satoshi", "KwYgW8gcxj1JWJXhPSu4Fqwzfhp5Yfi42mdYmMa4XqK7NJxXUSK7"
        );
        assertEquals(encoded, "6PYLtMnXvfG3oJde97zRyLYFZCYizPU5T3LwgdYJz1fRhh16bU7u6PPmY7");
        assertTrue(Bip38.isBip38PrivateKey(encoded));
    }


    public void testDecryptCompression2() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PYLtMnXvfG3oJde97zRyLYFZCYizPU5T3LwgdYJz1fRhh16bU7u6PPmY7", "Satoshi");
        assertEquals(decoded, "KwYgW8gcxj1JWJXhPSu4Fqwzfhp5Yfi42mdYmMa4XqK7NJxXUSK7");
    }


    public void testDecryptCompression2WithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PYLtMnXvfG3oJde97zRyLYFZCYizPU5T3LwgdYJz1fRhh16bU7u6PPmY7", "Satoshi");
        assertEquals(decoded, "KwYgW8gcxj1JWJXhPSu4Fqwzfhp5Yfi42mdYmMa4XqK7NJxXUSK7");
    }


    public void testDecryptNoCompressionWithEcMultiplyNoLot1() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PfQu77ygVyJLZjfvMLyhLMQbYnu5uguoJJ4kMCLqWwPEdfpwANVS76gTX",
                "TestingOneTwoThree");
        assertEquals(decoded, "5K4caxezwjGCGfnoPTZ8tMcJBLB7Jvyjv4xxeacadhq8nLisLR2");
    }


    public void testDecryptNoCompressionWithEcMultiplyNoLot1WithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PfQu77ygVyJLZjfvMLyhLMQbYnu5uguoJJ4kMCLqWwPEdfpwANVS76gTX",
                "TestingOneTwoThree");
        assertEquals(decoded, "5K4caxezwjGCGfnoPTZ8tMcJBLB7Jvyjv4xxeacadhq8nLisLR2");
    }


    public void testDecryptNoCompressionWithEcMultiplyNoLot2() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PfLGnQs6VZnrNpmVKfjotbnQuaJK4KZoPFrAjx1JMJUa1Ft8gnf5WxfKd", "Satoshi");
        assertEquals(decoded, "5KJ51SgxWaAYR13zd9ReMhJpwrcX47xTJh2D3fGPG9CM8vkv5sH");
    }


    public void testDecryptNoCompressionWithEcMultiplyNoLot2WithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PfLGnQs6VZnrNpmVKfjotbnQuaJK4KZoPFrAjx1JMJUa1Ft8gnf5WxfKd", "Satoshi");
        assertEquals(decoded, "5KJ51SgxWaAYR13zd9ReMhJpwrcX47xTJh2D3fGPG9CM8vkv5sH");
    }


    public void testDecryptNoCompressionWithEcMultiplyWithLot1() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("6PgNBNNzDkKdhkT6uJntUXwwzQV8Rr2tZcbkDcuC9DZRsS6AtHts4Ypo1j", "MOLON LABE");
        assertEquals(decoded, "5JLdxTtcTHcfYcmJsNVy1v2PMDx432JPoYcBTVVRHpPaxUrdtf8");
    }


    public void testDecryptNoCompressionWithEcMultiplyWithLot1WithBom() throws InterruptedException, AddressFormatException {
        String decoded = Bip38.decrypt("\uFEFF6PgNBNNzDkKdhkT6uJntUXwwzQV8Rr2tZcbkDcuC9DZRsS6AtHts4Ypo1j", "MOLON LABE");
        assertEquals(decoded, "5JLdxTtcTHcfYcmJsNVy1v2PMDx432JPoYcBTVVRHpPaxUrdtf8");
    }


    public void testDecryptNoCompressionWithEcMultiplyWithLot2() throws InterruptedException, UnsupportedEncodingException, AddressFormatException {
        // "MOLON LABE" using greek characters  = "ΜΟΛΩΝ ΛΑΒΕ"
        String passphrase = "\u039C\u039F\u039B\u03A9\u039D \u039B\u0391\u0392\u0395";
        assertEquals("ce9cce9fce9bcea9ce9d20ce9bce91ce92ce95", Utils.bytesToHexString(passphrase.getBytes("UTF-8")));
        String decoded = Bip38.decrypt("6PgGWtx25kUg8QWvwuJAgorN6k9FbE25rv5dMRwu5SKMnfpfVe5mar2ngH", passphrase);
        assertEquals(decoded, "5KMKKuUmAkiNbA3DazMQiLfDq47qs8MAEThm4yL8R2PhV1ov33D");
    }


    public void testDecryptNoCompressionWithEcMultiplyWithLot2WithBom() throws InterruptedException, UnsupportedEncodingException, AddressFormatException {
        // "MOLON LABE" using greek characters  = "ΜΟΛΩΝ ΛΑΒΕ"
        String passphrase = "\u039C\u039F\u039B\u03A9\u039D \u039B\u0391\u0392\u0395";
        assertEquals("ce9cce9fce9bcea9ce9d20ce9bce91ce92ce95", Utils.bytesToHexString(passphrase.getBytes("UTF-8")));
        String decoded = Bip38.decrypt("\uFEFF6PgGWtx25kUg8QWvwuJAgorN6k9FbE25rv5dMRwu5SKMnfpfVe5mar2ngH", passphrase);
        assertEquals(decoded, "5KMKKuUmAkiNbA3DazMQiLfDq47qs8MAEThm4yL8R2PhV1ov33D");
    }
}
