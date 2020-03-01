package io.ipfs.kotlin

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

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
