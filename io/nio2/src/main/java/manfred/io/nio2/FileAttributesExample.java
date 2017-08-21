package manfred.io.nio2;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.List;

/* This example demonstrates some of the functionality of the AttributeView API and related classes.
   The attribute views used in this example code should work on all platforms.
   The main and other methods do not catch any of the relevant exceptions thrown by the various API
   to make the example code easier to read. */
class FileAttributesExample {
    // Demonstrates the BasicFileAttributeView
    private static void basicAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- BasicFileAttributeView Example ----");

        // Get the BasicFileAttributeView and the BasicFileAttributes for this file
        BasicFileAttributeView basicView = Files.getFileAttributeView(attribPath, BasicFileAttributeView.class);
        BasicFileAttributes basicAttribs = basicView.readAttributes();

        // Print the attributes for this file
        System.out.println("File " + attribPath + " has the following BasicFileAttributes:");
        System.out.println("  Creation time = " + basicAttribs.creationTime());
        System.out.println("  Last access time = " + basicAttribs.lastAccessTime());
        System.out.println("  Last modified time = " + basicAttribs.lastModifiedTime());
        System.out.println("  Size = " + basicAttribs.size());
        System.out.println("  Directory? " + basicAttribs.isDirectory());
        System.out.println("  Regular file? " + basicAttribs.isRegularFile());
        System.out.println("  Symbolic link? " + basicAttribs.isSymbolicLink());
        System.out.println("  Other? " + basicAttribs.isOther());

        // Now move the last modified time a minute into the future!
        FileTime newModTime = FileTime.fromMillis(basicAttribs.lastModifiedTime().toMillis() + 60000);
        basicView.setTimes(newModTime, null, null);

        // Now re-read the attributes and print the file times again
        // The last modified time should be 1 minute ahead from the previous time it was printed
        basicAttribs = basicView.readAttributes();
        System.out.println("After changing the last modified time:");
        System.out.println("  Creation time = " + basicAttribs.creationTime());
        System.out.println("  Last access time = " + basicAttribs.lastAccessTime());
        System.out.println("  Last modified time = " + basicAttribs.lastModifiedTime());
        System.out.println();
    }

    // Demonstrates the FileOwnerAttributeView
    private static void fileOwnerAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- FileOwnerAttributeView Example ----");

        // Get the FileOwnerAttributeView and UserPrincipal for this file
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(attribPath, FileOwnerAttributeView.class);
        UserPrincipal owner = ownerView.getOwner();

        // Print the owner of this file
        System.out.println("The owner of this file is: " + owner);
        System.out.println();
    }

    // Demonstrates the FileStoreSpaceAttributeView
    private static void fileSpaceAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- FileStoreSpaceAttributeView Example ----");

        // Loop through the set of FileStores for the default FileSystem
        for (FileStore fileStore : FileSystems.getDefault().getFileStores()) {
            // Get the FileStoreSpaceAttributeView and FileStoreSpaceAttributes for this FileStore
            FileStoreAttributeView spaceView = fileStore.getFileStoreAttributeView(FileStoreAttributeView.class);
            System.out.println(spaceView);
            // Print the attributes for this FileStore
            System.out.println("FileStore " + fileStore + " has the following FileStoreAttributes:");
            System.out.println("   Total space = " + fileStore.getTotalSpace());
            System.out.println("   Unallocated space = " + fileStore.getUnallocatedSpace());
            System.out.println("   Usable space = " + fileStore.getUsableSpace());
        }
        System.out.println();
    }

    // Demonstrates the UserDefinedFileAttributeView
    private static void userAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- UserDefinedFileAttributeView Example ----");

        if (File.separatorChar != '\\') {
            System.out.println("This example is only available on Windows, sorry!");
            return;
        }

        // Get the UserDefinedFileAttributeView for this file
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(attribPath, UserDefinedFileAttributeView
                .class);

        // Get the list of user defined attribues for this file - we expect this to be empty
        List<String> attribList = userView.list();
        if (attribList.size() == 0) {
            System.out.println("The file " + attribPath + " has no user defined attributes yet, as expected");
        } else {
            System.out.println("ERROR: The file " + attribPath + " has some user defined attributes that were not " +
                    "expected");
        }

        // Now we'll set our own attribute on this file
        System.out.println("Setting a user defined attribute on " + attribPath);
        String name = "Our Attribute Name!";
        String value = "This is out attribute value! You can put anything you like in here...";
        userView.write(name, Charset.defaultCharset().encode(value));

        // Let's try inspecting the user defined attributes again
        attribList = userView.list();
        if (attribList.size() == 1) {
            System.out.println("The file " + attribPath + " now has 1 user defined attribute, as expected:");
            for (String attribName : attribList) {
                // Allocate a ByteBuffer large enough to hold the attribute's value
                ByteBuffer attribValue = ByteBuffer.allocate(userView.size(attribName));

                // Get the value of the attribute and display it
                userView.read(attribName, attribValue);
                attribValue.flip();
                System.out.println("   Attribute Name: " + attribName);
                System.out.println("   Attribute Value: " + Charset.defaultCharset().decode(attribValue).toString());
            }
        } else {
            System.out.println("ERROR: The file " + attribPath + " has " + attribList.size() + " user defined " +
                    "attribute(s), but we expected 1!");
        }

        // Now delete the user defined attribute we just created
        userView.delete(name);

        // And list the attributes again to check they're back to 0
        attribList = userView.list();
        if (attribList.size() == 0) {
            System.out.println("After deleting the attribute, the file " + attribPath + " has no user defined " +
                    "attributes left, as expected");
        } else {
            System.out.println("ERROR: The file " + attribPath + " has some user defined attributes that were not " +
                    "expected");
        }
    }

    public static void main(String[] args) throws Exception {
        // Create the file we will be using in this example
        File attribFile = new File("attribFile");
        Path attribPath = attribFile.toPath();
        attribFile.createNewFile();
        attribFile.deleteOnExit();

        // Execute example code for BasicFileAttributeView
        basicAttributesExample(attribPath);

        // Execute example code for FileOwnerAttributeView
        fileOwnerAttributesExample(attribPath);

        // Execute example code for FileStoreSpaceAttributeView
        fileSpaceAttributesExample(attribPath);

        // Execute example code for UserDefinedFileAttributeView
        userAttributesExample(attribPath);
    }
}
