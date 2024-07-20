package com.spark.ana;


// Copyright (c) 2020, Identix identix@identix.com.br
//
// Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

import com.spark.ana.service.AntenaConfig;
import com.spark.ana.service.TagReportListenerImplementation;
import reader.*;

import java.util.Iterator;
import java.util.Set;

// Example on how to write the EPC bank.
public class WriteEpc {

    public static void main(String[] args) {
        WriteEpc myEpcWriter = new WriteEpc();
        String oldValue = "";
        String newValue = "3035E1967C070C8000000073";

        myEpcWriter.writeEpc(oldValue, newValue);
    }


    public static void writeEpc(String oldValue, String newValue) {
        String currentEpc = "";

        if(AntenaConfig.reader == null
                //hfv @29sept2023:
                || !AntenaConfig.reader.isConnected
        ) {
            //subscribe/connect to the reader
            System.out.println("Initializing antena");
            AntenaConfig.initReader();
        }

        try {
            //get seen tags in reader
            Set<String> seenTags = ((TagReportListenerImplementation)AntenaConfig.reader.onCompleteListener).getTagList();

            // retrieve the first element in Set
            Iterator iterator = seenTags.iterator();
            if (iterator.hasNext()) {
                currentEpc = (String) iterator.next();
            }
            System.out.println("currentEpc = " + currentEpc);

            //write new tag
            boolean wasWrittenEpc = AntenaConfig.reader.writeEpc(new EpcWrite(currentEpc, newValue));

            if ( wasWrittenEpc ) {
                System.out.println("Epc write");
            } else {
                System.out.println("Epc no se escribio :(");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
