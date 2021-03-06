(ns puppetlabs.trapperkeeper.authorization.testutils
  (:require [puppetlabs.ssl-utils.simple :as ssl]))

(defn request
  "Builds a ring request"
  [path method certificate ip]
  {:uri path :request-method method :remote-addr ip :ssl-client-cert certificate})

(defn create-certificate
  [cn]
  (let [cacert (ssl/gen-self-signed-cert "my ca" 41 {:keylength 512})]
    (:cert (ssl/gen-cert cn cacert 42 {:keylength 512}))))

(def test-domain-cert (create-certificate "test.domain.org"))
(def test-other-cert (create-certificate "www.other.org"))
(def test-denied-cert (create-certificate "bad.guy.com"))
