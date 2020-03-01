package io.ipfs.kotlin

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * What is it : a test for peerId 
 * Result : QmZYVoscbWWJJZWy7Ue19iGXC5SRh3kune3gKSYHv3kzKn for EA
 * Author : Emile Achadde 25 février 2020 at 09:31:47+01:00
 * Revision : Emile Achadde 27 février 2020 at 08:22:18+01:00
 */

class TestPeerId : BaseIpfsWebserverTest() {

    @Test
    fun testPeerId() {
        // setup
        server.enqueue(MockResponse().setBody("{\"Key\":\"QmUfbdU5125Rdwc7pS1wrTwvPDkfyB2jra6xU78TRbeP37\"}\n"))

        // invoke
        val peerIdInfo = ipfs.peerid.peerId()

        // assert
        assertThat(peerIdInfo).isNotNull()
        assertThat(peerIdInfo!!.Key).isEqualTo("QmUfbdU5125Rdwc7pS1wrTwvPDkfyB2jra6xU78TRbeP37")

        val executedRequest = server.takeRequest()
        assertThat(executedRequest.path).isEqualTo("/config/Identity.PeerID")

    }
}
