<div align="center">
  <img src="Assets/logo.png" alt="Aello logo" width="160">

  <h1>Aello</h1>

  <h3>A privacy-respecting YouTube and YouTube Music client for Android with a local recommendation engine.</h3>

  <p>
    <a href="https://github.com/Niczzxo/Aello-Native/releases"><b>Download APK</b></a> ·
    <a href="https://github.com/Niczzxo/Aello-Native/wiki"><b>Documentation</b></a> ·
    <a href="#support--donations"><b>Donate</b></a> ·
    <a href="#translate"><b>Translations</b></a>
  </p>
</div>

---

## Why Aello?

Most open-source YouTube clients give you playback but no way to discover new content. You either use the official app and get tracked, or you use an alternative and lose recommendations entirely.

Aello gives you both. The recommendation engine learns what you like by analyzing your watch behavior locally. It never leaves your devices. You can inspect everything it knows about you, adjust it, or wipe it at any time.

---

## Features

### Video
- High-quality playback via ExoPlayer (Media3) with resolution switching (1080p, 720p, 480p, 360p)
- SponsorBlock — automatically skips sponsors, intros, outros, and filler
- DeArrow — replaces clickbait thumbnails and titles with community-sourced alternatives
- Return Youtube Dislike (RYD)
- Background playback — listen to audio with the screen off
- Picture-in-Picture — keep watching while using other apps
- Casting to smart TVs and streaming devices
- Playback speed control (0.25x to 2x)
- Video chapters with seek jumping
- Gesture controls for brightness, volume, and seeking
- Subtitles with customizable font size, color, and background
- Downloads with VP9, AV1, and standard format support
- Resume playback from where you left off

### Music
- Dedicated music player with album art and audio visualizations
- Queue management with add, remove, and reorder
- Shuffle and repeat (single/all)
- Persistent mini player across the app
- Synchronized lyrics display
- Fetches tracks from YouTube Music
- Playback speed control for music playback

### Recommendations (AelloNeuro Engine)
- Runs 100% on-device — no server, no telemetry, no account needed
- Learns from what you watch, skip, like, dislike, search for, and how long you watch
- Distinguishes weekday and weekend patterns, morning and night preferences
- Detects when you're getting bored of a topic and mixes in new content
- Prevents your feed from collapsing into the same 2-3 topics
- Surfaces related videos from your recent watches to create natural topic transitions
- Uses engagement signals (like-to-view ratios) to filter out low-quality content
- Full transparency dashboard — see what the algorithm knows and why it recommended something
- Export/import your entire recommendation profile as a file

### Library
- Local watch history
- Favorites and custom playlists
- Shorts feed with bookmarking
- Continue watching shelf
- Subscription management with cached feeds

### Privacy
- No Google account required
- No ads, analytics, or tracking
- All data stored locally on your device
- Import subscriptions and history from NewPipe
- Export or delete everything at any time

### Appearance
- 11 themes: Light, Dark, OLED Black, Ocean Blue, Forest Green, Sunset Orange, Purple Nebula, Midnight Black, Rose Gold, Arctic Ice, Crimson Red
- Built entirely with Jetpack Compose and Material 3

---

## Screenshots

<div align="center">
  <table>
    <tr>
      <td align="center"><b>Home Feed</b><br><img src="Assets/Home.jpeg" width="240"></td>
      <td align="center"><b>Video Player</b><br><img src="Assets/VideoPlayer.jpeg" width="240"></td>
      <td align="center"><b>Shorts</b><br><img src="Assets/Shorts.jpeg" width="240"></td>
    </tr>
    <tr>
      <td align="center"><b>Subscriptions</b><br><img src="Assets/Subscriptions.jpeg" width="240"></td>
      <td align="center"><b>Channel View</b><br><img src="Assets/Channel.jpeg" width="240"></td>
      <td align="center"><b>Playlist</b><br><img src="Assets/Playlist.jpeg" width="240"></td>
    </tr>
    <tr>
      <td align="center"><b>Music Hub</b><br><img src="Assets/Music.jpeg" width="240"></td>
      <td align="center"><b>Music Player</b><br><img src="Assets/MusicPlayer.jpeg" width="240"></td>
      <td align="center"><b>Lyrics</b><br><img src="Assets/Lyrics.jpeg" width="240"></td>
    </tr>
    <tr>
      <td align="center"><b>Artist Page</b><br><img src="Assets/Artist.jpeg" width="240"></td>
      <td align="center"><b>Album</b><br><img src="Assets/Album.jpeg" width="240"></td>
      <td align="center"><b>Your Library</b><br><img src="Assets/Library.jpeg" width="240"></td>
    </tr>
    <tr>
      <td align="center"><b>Your Taste</b><br><img src="Assets/YourTaste.jpeg" width="240"></td>
      <td align="center"></td>
      <td align="center"></td>
    </tr>
  </table>
</div>

---
## Download

### Stable Release

<p>
  <a href="https://github.com/Niczzxo/Aello-Native/releases/latest"><b>Download the latest release</b></a> ·
  <a href="https://apt.izzysoft.de/packages/io.github.niczzxo.aello">IzzyOnDroid</a> ·
  <a href="https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/Niczzxo/Aello-Native/">Obtainium</a>
</p>

#### Which APK should I download?

Each release has six APKs: three device types, each in a GitHub build and a FOSS build.

| Your device | GitHub build | FOSS build |
| :--- | :--- | :--- |
| Most phones and tablets (64-bit, `arm64-v8a`) | `aello-arm64-v8a.apk` | `aello-foss-arm64-v8a.apk` |
| Older 32-bit phones (`armeabi-v7a`) | `aello-armeabi-v7a.apk` | `aello-foss-armeabi-v7a.apk` |
| Any device (universal, larger download) | `aello.apk` | `aello-foss.apk` |

- **Not sure?** `aello-arm64-v8a.apk` works on almost every phone. `aello.apk` works on all of them.
- **GitHub build:** updates itself from inside the app and supports Discord Rich Presence.
- **FOSS build:** the same app without the built-in updater and the Discord integration. This is the build on IzzyOnDroid; update it through IzzyOnDroid or Obtainium.
- `checksums.txt` in each release lists the SHA-256 of every APK.

### Nightly Build
> ⚠️ Nightly builds are unstable and may contain bugs. Use at your own risk.

Aello Nightly is built from every commit to `main`. It installs next to the stable app as a separate app called **Aello Nightly**, keeps its own data, and updates itself to each new nightly from inside the app.

<p><a href="https://github.com/Niczzxo/Aello-Native/releases/tag/nightly"><b>Try the Nightly build</b></a></p>

### Requirements 
**Minimum Requirement:** Android 8.0+
<a id="cert"></a>
### Verifying Authenticity
To ensure the authenticity of the APK and verify it has not been tampered with, you can check the signing certificate fingerprint using tools like [AppVerifier](https://github.com/soupslurpr/AppVerifier).

**Release Certificate SHA-256 Fingerprint:**
`43:22:29:4E:D4:CA:A2:D4:29:41:40:09:58:18:08:0F:FE:8A:CC:1F:BE:3C:DC:76:10:7D:F4:5C:52:86:BE:40`

---

<a id="support--donations"></a>
## 💰 Support Development

Aello is a free and open-source project. If you like it, please star the repository on GitHub and share it with others — that is the best way to support its development.

---

## 🙏 Acknowledgments

Aello stands on the shoulders of giants. Special thanks to:

*   **[NewPipeExtractor](https://github.com/TeamNewPipe/NewPipeExtractor):** The backbone of our data extraction.
*   **[NewPipe](https://github.com/TeamNewPipe/NewPipe):** For inspiration from their solid foundation for YouTube data handling.
*   **[PipePipe](https://codeberg.org/NullPointerException/PipePipe):** For their SABR and InnerTube playback implementation, which guided Aello's YouTube streaming pipeline.
*   **[PipePipe Developer Docs](https://priveetee.github.io/Docs-PipePipe/):** For their reference documentation on SABR, BotGuard/PoToken attestation, and InnerTube extraction internals.
*   **[MetroList](https://github.com/MetrolistGroup/Metrolist):** Inspiration for the Hybrid Music fetching approach, Lyrics handling and some icons design references.
*   **[LibreTube](https://github.com/LibreTube/LibreTube):** Inspiration for SponsorBlock and DeArrow handling and some icons design references.
*   **[ExoPlayer](https://github.com/google/ExoPlayer):** The gold standard for Android media playback.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose):** For enabling the beautiful, modern UI.
*   **[Material Design 3](https://m3.material.io/):** For the design system and guidelines.

---

<div align="center">

<a id="translate"></a>
## Translations

Help translate Aello into your language! Translations live in `app/src/main/res/values-*/strings.xml` — contributions via pull requests are welcome.

---

## 📄 License & Copyright

**Aello** is Free Software: You can use, study, share, and improve it at your will.
It is distributed under the **GNU General Public License v3 (GPLv3)**.

**Copyright © 2025-2026 A-EDev**

> 🚨 **For Developers:**
> This license requires that any project using Aello's source code (including the `AelloNeuroEngine` algorithm) must also be **Open Source** under the GPLv3 license. You may not use this code in a proprietary or closed-source application.

---

## Star History

<a href="https://github.com/Niczzxo/Aello-Native/stargazers">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://assets.riyazulislam.dev/v1/stars/stars-flow-android-dark.svg">
    <img alt="Star history of Aello for Android" src="https://assets.riyazulislam.dev/v1/stars/stars-flow-android-light.svg" width="100%">
  </picture>
</a>

---

<div align="center">
  <a href="https://github.com/Niczzxo"><picture><source media="(prefers-color-scheme: dark)" srcset="https://assets.riyazulislam.dev/v1/badges/pill/part-of-flow-dark.svg"><img alt="Part of the Aello ecosystem" src="https://assets.riyazulislam.dev/v1/badges/pill/part-of-flow-light.svg" height="60"></picture></a>
  <br><br>
  <sub>Made with ❤️ by Niczzxo</sub>
</div>
