# BulkEmail

A Spring Boot application for sending bulk HTML emails via SMTP with configurable recipients, sender details, and per-email sleep intervals.

## Requirements

- Java 25
- Maven
- An SMTP server (e.g., Gmail, SendGrid)

## Project Structure

```
files/
  email.html           # HTML email body template
  mailboxes.txt        # Recipient list
  email.properties     # Sender/email configuration
  smtp.properties      # SMTP server configuration
```

## Configuration

Before running, copy the example files and fill in your values:

```bash
cp files/email.html.example     files/email.html
cp files/mailboxes.txt.example  files/mailboxes.txt
cp files/email.properties.example files/email.properties
cp files/smtp.properties.example  files/smtp.properties
```

### `files/smtp.properties`

| Property | Description |
|---|---|
| `mail.smtp.host` | SMTP server hostname |
| `mail.smtp.socketFactory.port` | SSL port (e.g. `465`) |
| `mail.transport.protocol` | Transport protocol (`smtp`) |
| `mail.smtp.auth` | Enable authentication (`true`) |
| `mail.debug` | Enable debug output |

### `files/email.properties`

| Property | Description |
|---|---|
| `email.username` | SMTP login username |
| `email.password` | SMTP login password |
| `email.from.name` | Sender display name |
| `email.from.address` | Sender email address |
| `email.bcc.name` | BCC recipient name |
| `email.bcc.address` | BCC recipient email |
| `email.replyto.name` | Reply-To name |
| `email.replyto.address` | Reply-To email address |
| `email.subject` | Email subject line |
| `email.sleeptime` | Seconds to wait between emails |

### `files/mailboxes.txt`

One recipient per line in `Name|email@address.com` format:

```
John Doe|john@example.com
Jane Smith|jane@example.com
```

### `files/email.html`

HTML template for the email body. Use `[[NOMBRE]]` as a placeholder — it will be replaced with each recipient's name at send time.

## Running

```bash
./mvnw spring-boot:run
```

The application sends emails to each recipient in order. It sleeps 60 seconds after the first email, then waits `email.sleeptime` seconds between subsequent emails.

## Build

```bash
./mvnw clean package
java -jar target/BulkEmail-0.0.1-SNAPSHOT.jar
```
