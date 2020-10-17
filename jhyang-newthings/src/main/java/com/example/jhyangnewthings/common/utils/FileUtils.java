package com.example.jhyangnewthings.common.utils;

import java.io.*;

/**
 * @author jhYang
 * @date 2019/8/17 0017
 */
public class FileUtils {

    /**
     * 按行读取文件(字符)
     *
     * @param filename
     */
    public static void ReadFileByLine(String filename) {

        File file = new File(filename);
        InputStream is = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            is = new FileInputStream(file);
            reader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(reader);
//            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
                if (null != reader)
                    reader.close();
                if (null != is)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 按字节读取文件
     *
     * @param filename
     */
    public static void ReadFileByBytes(String filename) {
        File file = new File(filename);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int index = 0;
            while (-1 != (index = is.read())) {
                System.out.write(index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------按字节读出-------------");
        try {
            is = new FileInputStream(file);
            //一次读多个字节
            byte[] tempbyte = new byte[1024];
            int index = 0;
            while (-1 != (index = is.read(tempbyte))) {

                //println（）输出字符  write()输出字节
                // write参数的含义：要输出的字符数组，起始下标和结束下标
                System.out.write(tempbyte, 0, index);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-----------按字节读出   2 -------------");
    }


    /**
     * 按字符读取文件
     *
     * @param filename
     */
    public static void ReadFileByChar(String filename) {
        File file = new File(filename);
        InputStream is = null;
        Reader isr = null;
        try {
            is = new FileInputStream(file);
            isr = new InputStreamReader(is);
            int index = 0;
            while (-1 != (index = isr.read())) {
                //字符 但是没有使用缓冲池
                System.out.print((char) index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
                if (null != isr)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        String filename = "D:/ceshi.txt";

//      ReadFileByLine(filename);
//      ReadFileByBytes(filename);
        ReadFileByChar(filename);
        System.out.println("------结束--------");


//         ReadFileByLine(filename);
//         ReadFileByBytes(filename);
//         ReadFileByChar(filename);
//        String writeFile = "javawrite2file.txt";
//        // Write2FileByOutputStream(writeFile);
//        // Write2FileByBuffered(writeFile);
//        Write2FileByFileWriter(writeFile);
    }

    /**
     * 通过OutputStreamWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByOutputStream(String filename) {
        File file = new File(filename);
        FileOutputStream fos = null;
        // BufferedOutputStream bos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.write("Write2FileByOutputStream");
            // bos = new BufferedOutputStream(fos);
            // bos.write("Write2FileByOutputStream".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过BufferedWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByBuffered(String filename) {
        File file = new File(filename);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write("Write2FileByBuffered");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过FileWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByFileWriter(String filename) {
        File file = new File(filename);
        FileWriter fw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write("Write2FileByFileWriter");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        String filename = "D:/testfile.txt";
//        // ReadFileByLine(filename);
//        // ReadFileByBytes(filename);
//        // ReadFileByChar(filename);
//        String writeFile = "javawrite2file.txt";
//        // Write2FileByOutputStream(writeFile);
//        // Write2FileByBuffered(writeFile);
//        Write2FileByFileWriter(writeFile);
//    }
}
