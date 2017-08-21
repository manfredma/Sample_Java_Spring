package manfred.io.nio2;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/* This example demonstrates some of the functionality of the WatchService API and related classes.
   The main method does not catch any of the relevant exceptions thrown by the various API
   to make the example code easier to read. */
class DirectoryWatchingExample {
    public static void main(String[] args) throws Exception {
        // Get a WatchService instance from the default FileSystem
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // Create our Path object and create the directory
        File watchDirFile = new File("watchDir");
        watchDirFile.mkdir();
        watchDirFile.deleteOnExit();
        Path watchDirPath = watchDirFile.toPath();

        // Now register the newly created directory with the WatchService
        WatchKey watchKey = watchDirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        Thread.sleep(500); // Sleep for half a second to allow the WatchService thread to start

        // Create a file inside our watched directory
        System.out.println("Creating file...");
        File tempFile = new File(watchDirFile, "tempFile");
        tempFile.createNewFile();

        // Now call take() and see if the event has been registered.
        // Note: Sometimes on file creation ENTRY_MODIFY events can also be created. This is OS dependent.
        WatchKey takeKey = watchService.take();
        if (takeKey != null) {
            for (WatchEvent<?> event : takeKey.pollEvents()) {
                System.out.println("An event was found after file creation of kind " + event.kind() + ". The event " +
                        "occurred on file " + event.context() + ".");
            }
        } else {
            System.out.println("ERROR: We did not get an event after file creation!");
        }
        System.out.println();

        // Modify the file without resetting the WatchKey - we should not get an event
        FileOutputStream fos = new FileOutputStream(tempFile);

        System.out.println("Modifying file...");
        fos.write(50); // Write a random byte to the file to modify it

        // We are not expecting an event because the watch key has not been reset after the last take() call.
        // Use poll() here for 2 seconds because take() will block forever until an event occurs, and we are not
        // expecting one!
        takeKey = watchService.poll(2, TimeUnit.SECONDS);
        if (takeKey == null) {
            System.out.println("No event after modification, as expected.");
        } else {
            System.out.println("ERROR: An unexpected event occurred after modification!");
        }
        System.out.println();

        // Reset the key so it can be queued by the WatchService again
        watchKey.reset();

        System.out.println("Modifying file again...");
        fos.write(50); // Write a byte to the file to modify it
        fos.close();

        // Since the watch key was reset before the modification, it should be returned by the take()
        // call this time. This key is also exactly the same object as the original key created by the register call
        takeKey = watchService.take();
        if ((takeKey != null) && (takeKey == watchKey)) {
            System.out.println("An event occurred after modification, as expected.");
            for (WatchEvent<?> event : takeKey.pollEvents()) {
                System.out.println("An event was found after file modification of kind " + event.kind() + ". The " +
                        "event occurred on file " + event.context() + ".");
            }
        } else {
            System.out.println("ERROR: We got null or a different WatchKey after modification!");
        }
        System.out.println();

        // Reset the key so it can be queued by the WatchService again
        watchKey.reset();

        // Delete the file
        System.out.println("Deleting file...");
        tempFile.delete();

        // This time we expect the take() call to return null even though the key has been reset, because
        // we did not register to watch delete events. On some OSes the take() call may return a watch key
        // with an ENTRY_MODIFY event for the deleted file, so check that case also.
        takeKey = watchService.take();
        if (takeKey == null) {
            System.out.println("No event after deletion, as expected.");
        } else {
            // We should not get an ENTRY_DELETE event, only ENTRY_MODIFY
            System.out.println("An event occurred after deletion, it should only be an ENTRY_MODIFY.");
            boolean modifyOnly = true;
            for (WatchEvent<?> event : takeKey.pollEvents()) {
                System.out.println("An event was found after file deletion of kind " + event.kind() + ". The event " +
                        "occurred on file " + event.context() + ".");
                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    modifyOnly = false;
                }
            }
            if (modifyOnly) {
                System.out.println("There were no ENTRY_DELETE events, as expected");
            } else {
                System.out.println("ERROR: We got an ENTRY_DELETE event we didn't register for!");
            }
        }

        // Cancel the watch key to stop watching its events
        watchKey.cancel();

        // Now close the WatchService instance to tidy up its resources before exiting
        watchService.close();

    }
}
