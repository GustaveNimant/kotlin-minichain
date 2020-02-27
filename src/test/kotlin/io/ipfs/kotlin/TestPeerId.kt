package io.ipfs.kotlin

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TestPeerId : BaseIpfsWebserverTest() {

    @Test
    fun testPeerId() {
        // setup
        server.enqueue(MockResponse().setBody("{\"key\":\"Qm123\"}\n"))

        // invoke
        val peerIdInfo = ipfs.config.peerId()

        // assert
        assertThat(PeerId).isNotNull()
        assertThat(PeerId!!.Key).isEqualTo("Qm123")

        val executedRequest = server.takeRequest()
        assertThat(executedRequest.path).isEqualTo("/config/Identity.PeerId")

    }
}
