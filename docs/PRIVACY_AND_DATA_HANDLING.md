# Privacy and Data Handling — MeritPrep 2.0 Alpha

## Current implementation

The current alpha is designed to work locally on the Android device.

- The Android manifest declares no network or other runtime permissions.
- The app does not request an account, email address, phone number or location.
- The current dependency list contains AndroidX/Jetpack Compose and JUnit; no analytics, advertising or remote-authentication SDK is included.
- The app has no cloud synchronisation or remote content service.
- `android:allowBackup="false"` is set in the manifest.

## Data stored on the device

The local SQLite database may store:

- lesson completion records;
- question attempts and topic-level objective-practice evidence;
- active CBT session question order, answers, flags and expiry time;
- mistake-notebook entries and review status;
- revision-card due dates, intervals and learner ratings;
- saved WAEC-style theory responses;
- study-plan activities and completion status.

These records are stored in the app's private data area. They are used to show progress, schedule revision and resume an unfinished practice session.

## User implications

- No learning record is currently sent to a MeritPrep server.
- Clearing app storage or uninstalling the app can erase local learning records.
- Automatic Android backup is disabled. Important written answers should be copied or saved separately until an export/import feature is provided.
- This alpha has not undergone an independent mobile security assessment.

## Requirements before cloud services or public release

Before adding accounts, remote sync, analytics or a production content-management service:

1. Publish a clear privacy notice and obtain appropriate consent.
2. Collect only data required for the stated learning purpose.
3. Use authenticated, encrypted transport and enforce authorisation on the server.
4. Protect sensitive data at rest and manage keys outside the APK.
5. Provide data export, correction and deletion workflows.
6. Define retention periods, backups, incident response and access logging.
7. Complete a threat model and independent Android/backend security review.
8. Apply child-privacy and applicable Nigerian data-protection requirements before collecting information from minors.

Do not add API secrets, service-account credentials or privileged database credentials to the Android application.
