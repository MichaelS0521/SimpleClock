//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        TimeZone displayTimeZone;

        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton buttonChangeFormat = new JButton("Change Clock Format");
        JButton buttonChangeTime = new JButton("Change local time/GMT");
        JPanel panel = new JPanel();

        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            panel.add(buttonChangeFormat);
            panel.add(buttonChangeTime);
            this.getContentPane().add(panel);
            this.setLayout(new FlowLayout());
            this.setSize(400, 270);
            this.setResizable(false);

            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 60));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,45));
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,40));

            buttonChangeFormat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleTimeFormat();
                }

            });

            buttonChangeTime.addActionListener(new ActionListener() {
                boolean showLocalTime = true;
                @Override
                public void actionPerformed(ActionEvent e) {
                    showLocalTime = !showLocalTime;

                    if (showLocalTime) {
                        displayTimeZone = TimeZone.getDefault();
                    }else {
                        displayTimeZone = TimeZone.getTimeZone("GMT");
                    }
                    updateTimeZone(displayTimeZone);

                    updateTimer();
                }
            });

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);
    
            setTimer();
        }


        private void toggleTimeFormat() {
            if (timeFormat == null || timeFormat.toPattern().equals("hh:mm:ss a")) {
                timeFormat = new SimpleDateFormat("k:mm:ss");
            }else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
            }
        }

        public void setTimer() {
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTimer();
                }
            });
            timer.start();
        }
    
        public void updateTimer() {

                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

        }

        private void updateTimeZone(TimeZone timeZone) {
            timeFormat.setTimeZone(timeZone);
            dayFormat.setTimeZone(timeZone);
            dateFormat.setTimeZone(timeZone);
        }

        public static void main(String[] args) {
            new SimpleClock();
        }
    }
