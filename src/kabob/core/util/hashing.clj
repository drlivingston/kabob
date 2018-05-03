(ns kabob.core.util.hashing
  (import java.security.MessageDigest
          java.math.BigInteger
          org.apache.commons.codec.binary.Base64))

;;;-------------------------------------------------------------------
;;; hashing utilities
;;;-------------------------------------------------------------------

(defn to-hashing-string [kb x]
  (or (and (symbol? x) (name x))
      (str x)))

;(defn sha-1
;  ([x] (Base64/encodeBase64URLSafeString
;        ^bytes (.digest (doto (MessageDigest/getInstance "SHA1")
;                          (.reset)
;                          (.update (.getBytes (str x) "UTF-8"))))))
;  ([x & rest] (sha-1 (apply str x rest))))

;;;-------------------------------------------------------------------
;;; end
;;;-------------------------------------------------------------------
