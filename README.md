# OTPAuth

A quick implementation of of "HOTP: HMAC-Based One-Time Password Algorithm", RFC 4226. Specifically designed to produce authentication codes for Google two-factor authentication.

# Usage

When setting up two-factor authentication with Google, first scan the QR code with a generic QR reader app. Type the URL decoded from your QR code into the file `~/.google`. You can put one URI per line. Then simply run the main class `net.drwille.otpauth.OTPAuth`.

# Building

Just use Maven to do a `mvn package`. This will create the jar.
