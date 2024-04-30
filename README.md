Powerfull library for rendering MS Office documents on Android.
Works with MS Word, Excel, PowerPoint, and PDF. No network needed.
Detailed description and steps for usage will be added soon. 

# Android-Office (replica)
This is a replica of [this](https://github.com/zjtone/Android-Office) old repository. 
I've packed that repository as a library, upped the Java version and target SDK, fixed a few errors, and solved many conflicts. 
Supported files: PDF, MS Word (doc, docx, and many other word formats), MS Excel (all formats), MS PowerPoint (PPT, PPTX, etc), and TXT.

### It's possible to:
* read files as is
* save pages as bitmap images
* search for some text in the document
* view slideshow (presentations only)

# How to add
First, you should add Jitpack.io as a possible repository.

### In the settings.gradle.kts:
```
repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
```

### And in the build.gradle.kts app level:
```
implementation("com.github.shprotx:AndroidOffice_replica:1.0.0-beta")
```

# How to use
...
