import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class StopwatchClock extends JFrame {
    private JLabel clockLabel;
    private JLabel stopwatchLabel;
    private JButton startButton, stopButton, resetButton;

    private Timer clockTimer;
    private Timer stopwatchTimer;

    private long stopwatchStartTime = 0;
    private long elapsedTime = 0;
    private boolean running = false;

    public StopwatchClock() {
        setTitle("Stopwatch and Clock");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Clock label
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        add(clockLabel, BorderLayout.NORTH);

        // Stopwatch label
        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stopwatchLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        add(stopwatchLabel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        clockTimer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            clockLabel.setText("Current Time: " + sdf.format(new Date()));
        });
        clockTimer.start();

        // Stopwatch timer
        stopwatchTimer = new Timer(1000, e -> updateStopwatch());
        
        // Button actions
        startButton.addActionListener(e -> startStopwatch());
        stopButton.addActionListener(e -> stopStopwatch());
        resetButton.addActionListener(e -> resetStopwatch());
    }

    private void startStopwatch() {
        if (!running) {
            stopwatchStartTime = System.currentTimeMillis() - elapsedTime;
            stopwatchTimer.start();
            running = true;
        }
    }

    private void stopStopwatch() {
        if (running) {
            elapsedTime = System.currentTimeMillis() - stopwatchStartTime;
            stopwatchTimer.stop();
            running = false;
        }
    }

    private void resetStopwatch() {
        stopwatchTimer.stop();
        running = false;
        elapsedTime = 0;
        stopwatchLabel.setText("00:00:00");
    }

    private void updateStopwatch() {
        long now = System.currentTimeMillis();
        long time = now - stopwatchStartTime;

        int seconds = (int) (time / 1000) % 60;
        int minutes = (int) (time / (1000 * 60)) % 60;
        int hours = (int) (time / (1000 * 60 * 60));

        stopwatchLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StopwatchClock app = new StopwatchClock();
            app.setVisible(true);
        });
    }
}
