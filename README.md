Powerfull library for rendering MS Office documents on Android.

# Android-Office (replica)
This is a replica of [this](https://github.com/zjtone/Android-Office) old repository. 
I've packed that repository as a library, upped the Java version and target SDK, fixed a few errors, and solved many conflicts. 
Supported files: PDF, MS Word (doc, docx, and many other word formats), MS Excel (all formats), MS PowerPoint (PPT, PPTX, etc), and TXT.
No network is needed - fully offline. 

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
You should have a valid file URI, looks as 
```
/storage/emulated/0/temp/document_name.pdf
```
WARNING! You shouldn't use FileProvider for getting URI.
Next, you just create OfficeApplication object and open the file :)
```
val officeApplication = OfficeApplication(context as Activity, myLinearLayout)
officeApplication.openFile(myFileURI)
```
If you want to do something with the file after opening, you can add OpenFileFinishListener.
```
officeApplication.addOpenFileFinishListener {
            viewModelScope.launch {
                delay(200)
                val bitmap = officeApplication.getSlideToImage(slideNumber) // or getPageToImage(pageNumber)
                doSomethingWithBitmap(bitmap)
            }
        }
```

PS: I recommend turning off hardware acceleration - Excel files will draw smoothly without it.

You can also use this library with Jetpack Compose and AndroidView:
```
AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = { context ->
                val view = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                }
                val application = OfficeApplication(context as Activity, view)
                application.openFile(myFileURI)
                application.addOpenFileFinishListener {
                    Handler().postDelayed({}, 200)
                    doSomething()
                }
                view
            },
            update = { view ->
                doSomethingElse()
            }
        )
```

or if you need only bitmaps you can get it in your ViewModel:
```
    val pagesBitmaps = mutableStateOf<List<Bitmap>>(emptyList())

    @SuppressLint("ResourceType", "InflateParams")
    fun createBitmapsFromPPTXDocument(context: Context) {
        val view = (context as Activity).layoutInflater.inflate(R.layout.office_document, null)
        val viewGroup = view.findViewById<LinearLayout>(R.id.officeDocLL)
        val officeApplication = OfficeApplication(context, viewGroup)
        officeApplication.addOpenFileFinishListener {
            viewModelScope.launch {
                delay(200)
                val list = mutableListOf<Bitmap>()
                for (i in 1..officeApplication.pagesCount) {
                    val page = officeApplication.getSlideToImage(i)
                    list.add(page)
                }
                pagesBitmaps.value = list.toList()
                isLoading.value = false
            }
        }
        officeApplication.openFile(myFileURI)
    }
```




Enjoy!
