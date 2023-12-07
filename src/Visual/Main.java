package Visual;
import javax.swing.*;
import java.awt.Color;
import java.awt.ComponentOrientation;//Espelhar imagem da barra de vida
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class Main {
	private static int spriteY = 192; // Altura padrão do sprite
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarEMostrarGUI());
    }
    

    /**
     * @wbp.parser.entryPoint
     */
    private static void criarEMostrarGUI() {
        JFrame frame = new JFrame("Street Fighter UESB");
        frame.setBounds(50, 50, 900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JPanel placeHolder = new JPanel();
        placeHolder.setBounds(0, 0, 884, 461);
        placeHolder.setBackground(Color.yellow);
        frame.getContentPane().add(placeHolder);
        placeHolder.setLayout(null);
        
        JProgressBar lifePlayer2 = new JProgressBar();
        lifePlayer2.setValue(75);
        lifePlayer2.setForeground(Color.GREEN);
        lifePlayer2.setBorder(new LineBorder(Color.YELLOW, 4));
        lifePlayer2.setBackground(Color.BLUE);
        lifePlayer2.setBounds(485, 11, 375, 30);
        lifePlayer2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        placeHolder.add(lifePlayer2);
        
        JProgressBar lifePlayer1 = new JProgressBar();
        lifePlayer1.setBorder(new LineBorder(Color.YELLOW, 4));
        lifePlayer1.setBackground(Color.BLUE);
        lifePlayer1.setForeground(Color.GREEN);
        lifePlayer1.setBounds(25, 11, 375, 30);
        lifePlayer1.setValue(90);
        placeHolder.add(lifePlayer1);

        JLabel personagem1 = new JLabel();
        personagem1.setBorder(new LineBorder(Color.RED, 2));//Borda para ver o hitbox
        personagem1.setIcon(new ImageIcon("C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\Base\\1.png"));
        personagem1.setBounds(9, 192, spriteY, 260);
        placeHolder.add(personagem1);
        
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon("C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Cenários\\1.jpg"));
        background.setBounds(0, 0, 884, 461);
        placeHolder.add(background);

        final int[] posX1 = {10};
        final AnimationState animationState = new AnimationState();
        final int delay = 90;

        InputMap inputMap = personagem1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = personagem1.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "moveRightPressed");
        actionMap.put("moveRightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isMoving = true;
                animationState.isWalkingBackward
                 = false;
                personagem1.setBounds(posX1[0], spriteY, 186, 260);
                posX1[0] += 10;
                if (posX1[0] >= 690) {
                	posX1[0] = 690;
                }
                
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "moveLeftPressed");
        actionMap.put("moveLeftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isMoving = true;
                animationState.isWalkingBackward = true;
                posX1[0] -= 10;
                personagem1.setBounds(posX1[0], spriteY, 186, 260);
                if (posX1[0] <= 10) {
                	posX1[0] = 10;
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "moveRightReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "moveLeftReleased");
        actionMap.put("moveRightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isMoving = false;
            }
        });
        actionMap.put("moveLeftReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isMoving = false;
            }
        });

      

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "socoFracoPressed");
        actionMap.put("socoFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isSocoFraco = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "socoFracoReleased");
        actionMap.put("socoFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "chuteFracoPressed");
        actionMap.put("chuteFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState.isChuteFraco = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "chuteFracoReleased");
        actionMap.put("chuteFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jumpPressed");
        actionMap.put("jumpPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço apertado");
                animationState.isJumping = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "jumpReleased");
        actionMap.put("jumpReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço solto");
            }
        });




        Thread animationThread = new Thread(() -> {
            int currentFrameForward = 1;
            int currentFrameBackward = 1;
            int currentFrameSocoFraco = 1;
            int currentFrameChuteFraco = 1;
            int currentFramePuloFrente = 1;
            int currentFrameJump = 1;

            while (true) {
            	
                if (animationState.isMoving) {
                    if (animationState.isWalkingBackward) {
                        currentFrameBackward = (currentFrameBackward % animationState.numFrames) + 1;
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\AndarEsquerda\\" + currentFrameBackward + ".png";
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else {
                        currentFrameForward = (currentFrameForward % animationState.numFrames) + 1;
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\AndarDireita\\" + currentFrameForward + ".png";
                        personagem1.setIcon(new ImageIcon(imagePath));
                    }
                } else if (animationState.isJumping) {
                	if (currentFrameJump <= animationState.numJumpFrames) {
                        int spriteHeight = animationState.getSpriteHeightForJump(currentFrameJump);
                        spriteY = animationState.getSpriteYForPuloFrente(currentFrameJump);
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\PuloFrente\\" + currentFrameJump + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, spriteHeight);
                        personagem1.setIcon(new ImageIcon(imagePath));
                        System.out.println("Altura do pulo para o frame " + currentFrameJump + ": " + spriteHeight);
                        System.out.println("spriteY = " + spriteY);
                        currentFrameJump++;
                        if(currentFrameJump > 8) {
                        	animationState.isJumping = false;
                        }
                    } else {
                        animationState.isJumping = false; // Sair do estado de pulo
                        currentFrameJump = 1; // Reiniciar contador para o próximo pulo
                        spriteY = 192; // Retornar à posição Y de 192
                    }
                } else if (animationState.isSocoFraco) {
                	if (currentFrameSocoFraco == 2) {
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 258, 260);
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else if (currentFrameSocoFraco == 3) {
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, 260);
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else if (animationState.isPuloFrente) {
                        currentFramePuloFrente = (currentFramePuloFrente % animationState.numPuloFrenteFrames) + 1;
                        int spriteHeight = animationState.getSpriteHeightForJump(currentFramePuloFrente);
                        System.out.println("Sprite Height for Frame " + currentFramePuloFrente + ": " + spriteHeight);
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\PuloFrente\\" + currentFramePuloFrente + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, spriteHeight);
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else {
                        String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, 260);
                        personagem1.setIcon(new ImageIcon(imagePath));
                        animationState.isSocoFraco = false;
                    }
                    currentFrameSocoFraco = (currentFrameSocoFraco % animationState.numSocoFracoFrames) + 1;
                } else if (animationState.isChuteFraco) {
                    currentFrameChuteFraco = (currentFrameChuteFraco % animationState.numChuteFracoFrames) + 1;
                    String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\ChuteFraco\\" + currentFrameChuteFraco + ".png";
                    if (currentFrameChuteFraco == 3) {
                        personagem1.setBounds(posX1[0], spriteY, 317, 260);
                    } else if(currentFrameChuteFraco == 5) {
                    	animationState.isChuteFraco = false;
                    } else {
                    	
                    	//
                        personagem1.setBounds(posX1[0], spriteY, 186, 260);
                    }
                    personagem1.setIcon(new ImageIcon(imagePath));
                } else {//se ele não estiver fazendo nada, ele cai aqui
                	int coordenadaLocal = personagem1.getX();
                	currentFrameJump = 1;
                	spriteY = 192;
                    personagem1.setBounds(coordenadaLocal, spriteY, 186, 260);//Obrigar o personagem a parar no chão e com a hitbox original (animação base)
                    System.out.println("X = " + coordenadaLocal + " Y = " + spriteY); //186x260
                	
                    animationState.baseFrame = (animationState.baseFrame % animationState.numBaseFrames) + 1;
                    String imagePath = "C:\\Users\\Rodrigo\\Desktop\\Sprites Street Fighter\\Ryu\\Base\\" + animationState.baseFrame + ".png";
                    personagem1.setIcon(new ImageIcon(imagePath));
                    
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        animationThread.start();

        frame.setVisible(true);
    }

    private static class AnimationState {
        boolean isMoving = false;
        boolean isWalkingBackward = false;
        boolean isSocoFraco = false;
        int baseFrame = 1;
        int numBaseFrames = 4; // Número de frames para a animação base
        int numFrames = 6; // Número de frames para a animação de caminhar
        int numSocoFracoFrames = 3; // Número de frames para a animação de soco fraco
        boolean isChuteFraco = false;
        int numChuteFracoFrames = 5; // Número de frames para a animação de chute fraco
        boolean isPuloFrente = false;
        int numPuloFrenteFrames = 8; // Número de frames para a animação de pulo para frente
        boolean isJumping = false;
        int numJumpFrames = 6; // Número de frames para a animação de pulo
        
        private int getSpriteHeightForJump(int frame) {
        	switch (frame) {
	            case 1: return 260;
	            case 2: return 287;
	            case 3: return 252;
	            case 4: return 217;
	            case 5: return 202;
	            case 6: return 249;
	            case 7: return 298;
	            default: return 260; // Altura padrão 
	        }
        }
        
        private int getSpriteYForPuloFrente(int frame) {
            switch (frame) {
	            case 1: return 192;
	            case 2: return 162;
	            case 3: return 142;
	            case 4: return 122;
	            case 5: return 125;
	            case 6: return 145;
	            case 7: return 162;
	            default: return 192; // Altura padrão 
            }
        }
    }
}