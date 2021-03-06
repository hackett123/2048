package core.view.gui;

import core.util.Rank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GuiRenderer implements Runnable, IGuiRenderer {

    //GUI comps
    private JFrame mFrame;
    private JPanel mContainer, mGamePanel, mStatusPanel;
    private JLabel[][] mTiles;
    private JLabel mScoreLabel;


    //Constants
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;

    //GUI Constants
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;
    private static final Dimension DIM_CONTAINER = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    private static final Dimension DIM_PANEL_GAME = new Dimension(FRAME_WIDTH, FRAME_HEIGHT * 3 / 4);
    private static final Dimension DIM_PANEL_STATUS = new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 4);
    private final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 48);
    private final Font MEDIUM_FONT = new Font(Font.DIALOG, Font.BOLD, 24);
    private final Font SMALL_FONT = new Font(Font.DIALOG, Font.BOLD, 16);

    //Colors are up for debatefinal
    private Color COLOR_BACKGROUND = new Color(0x252f3f);
    private Color EMPTY_COLOR = new Color(0x808dad);
    private final Color[] RANK_COLORING = {
            new Color(0x335393),
            new Color(0x7facd6),
            new Color(0xbfb8da),
            new Color(0xe8b7d4),
            new Color(0xa5678e),
            new Color(0xff7b89),
            new Color(0x8a5082),
            new Color(0x6f5f90),
            new Color(0x758eb7),
            new Color(0xa5cad2)
    };

    private boolean justSentMove = true;
    private String lastMove = "w";

    private void sendMove(String move) {
        if (valid(move)) {
            justSentMove = false;
            lastMove = move;
        }
    }

    private boolean valid(String move) {
        return Pattern.matches("[wasdWASD]", move);
    }

    private boolean justSentMove() {
        return justSentMove;
    }

    @Override
    public String getMove() {
        while (justSentMove()) {
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        justSentMove = true;
        return lastMove;

    }

    private void setTileRanks(Rank[] ranks, List<String> values) {
        int[][] calculatedValuesMap = new int[Rank.values().length - 1][2]; // fills with 0 -> 0 for all ranks

        for (Rank rank : ranks) {
            int ord = rank.ordinal();
            if (calculatedValuesMap[ord][0] == 0) {
                calculatedValuesMap[ord][0] = 1;
                calculatedValuesMap[ord][1] = (int) Math.pow(2, ord);
            }
            values.add("" + calculatedValuesMap[ord][1]);
        }
    }

    @Override
    public void renderBoardState(Rank[] ranks, int score) {
        for (Rank rank : ranks) {
            if (rank == null) {
                throw new IllegalArgumentException("Provided null rank in array");
            }
        }

        List<String> values = new ArrayList<>();
        //convert to 2d array for the powers of two.
        setTileRanks(ranks, values);

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                String value = values.get(i * NUM_ROWS + j);
                String toShow = value.equals("1") ? "" : value;
                mTiles[i][j].setText(toShow);
                int rankOrdinal = ranks[i * NUM_ROWS + j].ordinal();
                Color color = rankOrdinal == 0 ? EMPTY_COLOR : RANK_COLORING[rankOrdinal % RANK_COLORING.length];
                mTiles[i][j].setBackground(color);
            }
        }
        renderScore(score);
    }

    private void renderScore(int score) {
        mScoreLabel.setText("" + score);
    }

    @Override
    public void run() {
        //put together gui framework
        buildContainers();
        addGamePanelComps();
        addStatusPanelComps();
        assembleAll();
        ship();
    }


    public GuiRenderer() {
        run();
    }

    private void buildContainers() {
        mFrame = new JFrame("2048 v1.0.1 MHSProductions");
        mContainer = new JPanel();
        mContainer.setPreferredSize(DIM_CONTAINER);
        mContainer.setBackground(COLOR_BACKGROUND);

        mGamePanel = new JPanel();
        mGamePanel.setPreferredSize(DIM_PANEL_GAME);
        mGamePanel.setBackground(COLOR_BACKGROUND);

        mStatusPanel = new JPanel();
        mStatusPanel.setPreferredSize(DIM_PANEL_STATUS);
        mStatusPanel.setBackground(COLOR_BACKGROUND);

        mFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                sendMove("" + (e.getKeyChar()));
            }
            @Override
            public void keyPressed(KeyEvent e) {
                }
            @Override
            public void keyReleased(KeyEvent e) {
                }
        });
    }

    private void addGamePanelComps() {
        mGamePanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        mTiles = new JLabel[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                JLabel currTile = new JLabel("0", SwingConstants.CENTER);
                mTiles[i][j] = currTile;
                currTile.setBackground(COLOR_BACKGROUND);
                currTile.setFont(MEDIUM_FONT);
                currTile.setOpaque(true);
                mGamePanel.add(currTile);
            }
        }
    }

    private void addStatusPanelComps() {
        mScoreLabel = new JLabel("0", SwingConstants.CENTER);
        mScoreLabel.setBackground(COLOR_BACKGROUND);
        mScoreLabel.setFont(MEDIUM_FONT);
        mScoreLabel.setOpaque(true);
        mStatusPanel.add(mScoreLabel);
    }

    private void assembleAll() {
        mContainer.setLayout(new BorderLayout());
        mContainer.add(mStatusPanel, BorderLayout.NORTH);
        mContainer.add(mGamePanel, BorderLayout.CENTER);
        mContainer.setVisible(true);
        mFrame.add(mContainer);
    }

    private void ship() {
        mFrame.setResizable(true);
        mFrame.pack();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
    }

}
