package controller;


import java.io.*;


public class RedesController
{
    public RedesController()
    {
        super();
    }


    // 1
    private String os()
    {
        return System.getProperty("os.name");
    }


    // 2
    public void ip()
    {
        String cmd_win = "ipconfig";
        String cmd_unix = "ip a";

        if (is_linux())
        {
            show_ip(cmd_unix, "inet");
        }
        else if (is_windows())
            show_ip(cmd_win, "ipv4");
    }


    // 3
    public void ping()
    {
        String cmd_win = "ping -4 -n 10 www.google.com.br";
        String cmd_unix = "ping -4 -c 10 www.google.com.br";

        if (is_linux())
            show_ping(cmd_unix, "/");
        else if (is_windows())
            show_ping(cmd_win, " ");
    }


    private boolean is_linux()
    {
        String systemName = os().toLowerCase();
        return (systemName.contains("linux"));
    }


    private boolean is_windows()
    {
        String systemName = os().toLowerCase();
        return (systemName.contains("windows"));
    }


    private void show_ip(String cmd, String ipv4_identifier)
    {
        String name = null;
        String ipv4;
        String exit_lines = callProcess(cmd);
        String[] lines = exit_lines.split("\n");
        char startLine;

        for (String exit : lines)
        {
            exit = exit.toLowerCase();
            startLine = exit.charAt(0);
            if (startLine != ' ')
                name = exit;
            if (name != null && exit.contains(ipv4_identifier))
            {
                ipv4 = exit;
                System.out.println(name);
                System.out.println(ipv4);
                name = null;
            }
        }
    }


    private void show_ping(String cmd, String identifier)
    {
        String[] times;
        String exit_lines = callProcess(cmd);
        String[] lines = exit_lines.split("\n");
        int last_position = lines.length - 1;

        times = lines[last_position].split(identifier);
        last_position = times.length - 1;
        System.out.println(times[last_position]);
    }


    private String callProcess(String process)
    {
        try
        {
            Process p = Runtime.getRuntime().exec(process);
            InputStream flow = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(flow);
            BufferedReader buffer = new BufferedReader(reader);

            String line = buffer.readLine();
            StringBuffer lines = new StringBuffer();

            while (line != null)
            {
                lines.append(line);
                lines.append("\n");
                line = buffer.readLine();
            }
            flow.close();
            reader.close();
            buffer.close();
            return (lines.toString());
        }
        catch (Exception e)
        {
            String msg_error = e.getMessage();
            System.err.println(msg_error);
        }
        return ("");
    }
}

