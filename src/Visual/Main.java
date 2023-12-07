package Visual;

import javax.swing.*;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.Font;

public class Main {
    private static int spriteY = 192; // Altura padrão do sprite
    
    public static String direcaoA = "";
    public static String direcaoB = "";
    public static int vida1 = 100;
    public static int vida2 = 100;
    
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

        

        JProgressBar lifePlayer1 = new JProgressBar();
        lifePlayer1.setBorder(new LineBorder(Color.YELLOW, 4));
        lifePlayer1.setBackground(Color.BLUE);
        lifePlayer1.setForeground(Color.GREEN);
        lifePlayer1.setBounds(25, 11, 375, 30);
        lifePlayer1.setValue(vida1);
        placeHolder.add(lifePlayer1);
        
        JProgressBar lifePlayer2 = new JProgressBar();
        lifePlayer2.setValue(vida2);
        lifePlayer2.setForeground(Color.GREEN);
        lifePlayer2.setBorder(new LineBorder(Color.YELLOW, 4));
        lifePlayer2.setBackground(Color.BLUE);
        lifePlayer2.setBounds(485, 11, 375, 30);
        lifePlayer2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        placeHolder.add(lifePlayer2);

        // JLabel do Ryu
        JLabel personagem1 = new JLabel();
        //personagem1.setBorder(new LineBorder(Color.RED, 2));// Borda para ver o hitbox
        personagem1.setIcon(new ImageIcon("src\\Sprites Street Fighter\\Ryu\\" + direcaoA + "\\Base\\1.png"));
        personagem1.setBounds(9, 192, spriteY, 260);
        placeHolder.add(personagem1);

        // JLabel do Ken
        JLabel personagem2 = new JLabel();
       // personagem2.setBorder(new LineBorder(Color.RED, 2));
        personagem2.setIcon(new ImageIcon("src\\Sprites Street Fighter\\Ken\\" + direcaoB +  "Base\\1.png"));
        personagem2.setBounds(650, 192, spriteY, 260);
        placeHolder.add(personagem2);       
        
        JLabel NomeRyu = new JLabel("");
        NomeRyu.setIcon(new ImageIcon("src\\Sprites Street Fighter\\Ryu\\nome.png"));
        NomeRyu.setFont(new Font("Tahoma", Font.PLAIN, 32));
        NomeRyu.setBounds(20, 42, 119, 50);
        placeHolder.add(NomeRyu);
        
        JLabel NomeKen = new JLabel("");
        NomeKen.setIcon(new ImageIcon("src\\Sprites Street Fighter\\Ken\\nome.png"));
        NomeKen.setFont(new Font("Tahoma", Font.PLAIN, 32));
        NomeKen.setBounds(778, 42, 119, 50);
        placeHolder.add(NomeKen);
        
        

        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon("src\\Sprites Street Fighter\\Cenários\\1.jpg"));
        background.setBounds(0, 0, 884, 461);
        placeHolder.add(background);

        
        final int[] posX1 = {10};
        final AnimationState animationState1 = new AnimationState();
        final int delay = 90;
        
        final int[] posX2 = {650};
        final AnimationState animationState2 = new AnimationState();        

        InputMap inputMap = personagem1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = personagem1.getActionMap();
        
        InputMap inputMap2 = personagem2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap2 = personagem2.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "moveRightPressed");
        actionMap.put("moveRightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isMoving = true;
                animationState1.isWalkingBackward
                 = false;
                personagem1.setBounds(posX1[0], spriteY, 186, 260);
                posX1[0] += 10;
                if (posX1[0] >= 690) {
                	posX1[0] = 690;
                }
                
            }
        });

        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0, false), "moveRightPressed");
        actionMap2.put("moveRightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isMoving = true;
                animationState2.isWalkingBackward = false;
                personagem2.setBounds(posX2[0], spriteY, 186, 260);
                posX2[0] += 10;
                if (posX2[0] >= 690) {
                    posX2[0] = 690;
                }
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "moveLeftPressed");
        actionMap.put("moveLeftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isMoving = true;
                animationState1.isWalkingBackward = true;
                posX1[0] -= 10;
                personagem1.setBounds(posX1[0], spriteY, 186, 260);
                if (posX1[0] <= 10) {
                	posX1[0] = 10;
                }
            }
        });

        
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0, false), "moveLeftPressed");
        actionMap2.put("moveLeftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isMoving = true;
                animationState2.isWalkingBackward = true;
                posX2[0] -= 10;
                personagem2.setBounds(posX2[0], spriteY, 186, 260);
                if (posX2[0] <= 10) {
                    posX2[0] = 10;
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "moveRightReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "moveLeftReleased");
        actionMap.put("moveRightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isMoving = false;
            }
        });
        actionMap.put("moveLeftReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isMoving = false;
            }
        });
        
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0, true), "moveRightReleased");
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0, true), "moveLeftReleased");
        actionMap2.put("moveRightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isMoving = false;
            }
        });
        actionMap2.put("moveLeftReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isMoving = false;
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "socoFracoPressed");
        actionMap.put("socoFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isSocoFraco = true;
            }
        });

        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), "socoFracoPressed");
        actionMap2.put("socoFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isSocoFraco = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "socoFracoReleased");
        actionMap.put("socoFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, true), "socoFracoReleased");
        actionMap2.put("socoFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "chuteFracoPressed");
        actionMap.put("chuteFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState1.isChuteFraco = true;
            }
        });

        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0, false), "chuteFracoPressed");
        actionMap2.put("chuteFracoPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                animationState2.isChuteFraco = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "chuteFracoReleased");
        actionMap.put("chuteFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0, true), "chuteFracoReleased");
        actionMap2.put("chuteFracoReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jumpPressed");
        actionMap.put("jumpPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço apertado");
                animationState1.isJumping = true;
            }
        });

        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0, false), "jumpPressed");
        actionMap2.put("jumpPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço apertado");
                animationState2.isJumping = true;
            }
        });
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "jumpReleased");
        actionMap.put("jumpReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço solto");
            }
        });

        inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0, true), "jumpReleased");
        actionMap2.put("jumpReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Espaço solto");
            }
        });

        
        
        Thread animationThread1 = new Thread(() -> {
            int currentFrameForward = 1;
            int currentFrameBackward = 1;
            int currentFrameSocoFraco = 1;
            int currentFrameChuteFraco = 1;
            int currentFramePuloFrente = 1;
            int currentFrameJump = 1;
            
            while (true) {
            	 if(posX1[0] > posX2[0]) {
                 	direcaoA = "Esquerda";
                 	direcaoB = "Direita";
                 }else if(posX1[0] < posX2[0]){
                 	direcaoA = "Direita";
                 	direcaoB = "Esquerda";
                 }else {
                 	// Mantém
                 }
            	 
            	if (animationState1.isSocoFraco || animationState1.isChuteFraco) {
            		//System.out.println(direcaoA);
            		if(direcaoA == "Direita") {
            			if (posX1[0] + personagem1.getWidth() >= posX2[0] && posX1[0] <= posX2[0] + personagem2.getWidth()) {
            				System.out.println("Ryu acertou Ken!");
            				vida2 = vida2 -5;
            				lifePlayer2.setValue(vida2);
            			}	
            		}
            		else {
            			if (posX1[0] <= posX2[0] + personagem1.getWidth()) {
            				System.out.println("Ryu acertou Ken!");
            				vida2 = vida2 -10;
            				lifePlayer2.setValue(vida2);
            			}	
            		}
        	    }
            	
            	if (vida1 <= 0) {
            		showMessageDialog(null, "Ken Venceu!");
            		vida1 = 100;
            		vida2 = 100;
            		personagem1.setBounds(posX1[0], 192, spriteY, 260);
            		personagem2.setBounds(posX2[0], 192, spriteY, 260);
            		frame.repaint();
            		frame.revalidate();
            		posX1[0] = 9;
            		posX2[0] = 650;
            		lifePlayer1.setValue(vida1);
            		lifePlayer2.setValue(vida2);
            		personagem2.setBounds(posX2[0], 192, spriteY, 260);
            	}
				if (vida2 <= 0) {
					showMessageDialog(null, "Ryu Venceu!");
					vida1 = 100;
            		vida2 = 100;
            		personagem1.setBounds(posX1[0], 192, spriteY, 260);
            		personagem2.setBounds(posX2[0], 192, spriteY, 260);
            		frame.repaint();
            		frame.revalidate();
            		posX1[0] = 9;
            		posX2[0] = 650;
            		lifePlayer1.setValue(vida1);
            		lifePlayer2.setValue(vida2);
            		personagem2.setBounds(posX2[0], 192, spriteY, 260);
            	}
            	
                if (animationState1.isMoving) {
                    if (animationState1.isWalkingBackward) {
                        currentFrameBackward = (currentFrameBackward % animationState1.numFrames) + 1;
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA + "\\AndarEsquerda\\" + currentFrameBackward + ".png";
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else {
                        currentFrameForward = (currentFrameForward % animationState1.numFrames) + 1;
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA +"\\AndarDireita\\" + currentFrameForward + ".png";
                        personagem1.setIcon(new ImageIcon(imagePath));
                    }
                } else if (animationState1.isJumping) {
                	//
                	//
                	//
                	//
                	if (currentFrameJump <= animationState1.numJumpFrames) {
                        int spriteHeight = animationState1.getSpriteHeightForJump(currentFrameJump);
                        spriteY = animationState1.getSpriteYForPuloFrente(currentFrameJump);
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA +"\\PuloFrente\\" + currentFrameJump + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, spriteHeight);
                        personagem1.setIcon(new ImageIcon(imagePath));
                        //System.out.println("Altura do pulo para o frame " + currentFrameJump + ": " + spriteHeight);
                        //System.out.println("spriteY = " + spriteY);
                        currentFrameJump++;
                        if(currentFrameJump > 8) {
                        	animationState1.isJumping = false;
                        }
                    } else {
                        animationState1.isJumping = false; // Sair do estado de pulo
                        currentFrameJump = 1; // Reiniciar contador para o próximo pulo
                        spriteY = 192; // Retornar à posição Y de 192
                    }
                	//
                	//
                	//
                	//
                } else if (animationState1.isSocoFraco) {
                	
                	
                	if (currentFrameSocoFraco == 2) {
                		if(direcaoA == "Direita") {
                			String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA +"\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                            personagem1.setBounds(posX1[0], spriteY, 258, 260);
                            personagem1.setIcon(new ImageIcon(imagePath));
                		}
                		else {
                			String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA +"\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                            personagem1.setBounds(posX1[0] - 75, spriteY, 258, 260);
                            personagem1.setIcon(new ImageIcon(imagePath));
                		}
                        
                    } else if (currentFrameSocoFraco == 3) {
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA + "\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, 260);
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else if (animationState1.isPuloFrente) {
                        currentFramePuloFrente = (currentFramePuloFrente % animationState1.numPuloFrenteFrames) + 1;
                        int spriteHeight = animationState1.getSpriteHeightForJump(currentFramePuloFrente);
                        System.out.println("Sprite Height for Frame " + currentFramePuloFrente + ": " + spriteHeight);
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\" + direcaoA + "\\PuloFrente\\" + currentFramePuloFrente + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, spriteHeight);
                        personagem1.setIcon(new ImageIcon(imagePath));
                    } else {
                        String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA + "\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem1.setBounds(posX1[0], spriteY, 186, 260);
                        personagem1.setIcon(new ImageIcon(imagePath));
                        animationState1.isSocoFraco = false;
                    }
                    currentFrameSocoFraco = (currentFrameSocoFraco % animationState1.numSocoFracoFrames) + 1;
                } else if (animationState1.isChuteFraco) {
                    currentFrameChuteFraco = (currentFrameChuteFraco % animationState1.numChuteFracoFrames) + 1;
                    String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA + "\\ChuteFraco\\" + currentFrameChuteFraco + ".png";
                    if (currentFrameChuteFraco == 3) {
                    	if(direcaoA == "Direita") {
                    		personagem1.setBounds(posX1[0], spriteY, 317, 260);
                		}
                		else {
                			personagem1.setBounds(posX1[0] - 131, spriteY, 317, 260);
                		}                   	

                    } else if(currentFrameChuteFraco == 5) {
                    	animationState1.isChuteFraco = false;
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
                    //System.out.println("X = " + coordenadaLocal + " Y = " + spriteY); //186x260
                	
                    animationState1.baseFrame = (animationState1.baseFrame % animationState1.numBaseFrames) + 1;
                    String imagePath = "src\\Sprites Street Fighter\\Ryu\\"+ direcaoA + "\\Base\\" + animationState1.baseFrame + ".png";
                    personagem1.setIcon(new ImageIcon(imagePath));
                    
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        Thread animationThread2 = new Thread(() -> {
            int currentFrameForward = 1;
            int currentFrameBackward = 1;
            int currentFrameSocoFraco = 1;
            int currentFrameChuteFraco = 1;
            int currentFramePuloFrente = 1;
            int currentFrameJump = 1;
            
            while (true) {
            	
            	 if(posX1[0] > posX2[0]) {
                 	direcaoA = "Direita";
                 	direcaoA = "Esquerda";
                 }else if(posX1[0] < posX2[0]){
                 	direcaoA = "Esquerda";
                 	direcaoA = "Direita";
                 }else {
                 	// Mantém
                 }
            	
            	 if (animationState2.isSocoFraco || animationState2.isChuteFraco) {
             		//System.out.println(direcaoB);
            		//System.out.println("X1 = " + posX1[0]);
            		//System.out.println("X2 = " + posX2[0]);
            		//System.out.println("Largura X1 = " + personagem1.getWidth());
            		//System.out.println("X1 + Largura X1 = " + (posX1[0] + personagem1.getWidth()));
             		if(direcaoB == "Esquerda") {
             			if (posX2[0] <= posX1[0] + personagem1.getWidth() + 75) {
             				System.out.println("Ken acertou Ryu!");
             				if(currentFrameSocoFraco == 2) {
             					vida1 = vida1 -5;
             					lifePlayer1.setValue(vida1);
             				}
             				
             			}	
             		}
             		else {
             			if (posX2[0] + personagem2.getWidth() >= posX1[0] && posX2[0] <= posX1[0] + personagem1.getWidth()) {
             				System.out.println("Ken acertou Ryu!");
             				if(currentFrameChuteFraco == 3) {
             					vida1 = vida1 -10;
            				lifePlayer1.setValue(vida1);
             				}
             				
             			}	
             		}
         	    }
            	
                if (animationState2.isMoving) {
                    if (animationState2.isWalkingBackward) {
                        currentFrameBackward = (currentFrameBackward % animationState2.numFrames) + 1;
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB +"\\AndarEsquerda\\" + currentFrameBackward + ".png";
                        personagem2.setIcon(new ImageIcon(imagePath));
                    } else {
                        currentFrameForward = (currentFrameForward % animationState2.numFrames) + 1;
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\AndarDireita\\" + currentFrameForward + ".png";
                        personagem2.setIcon(new ImageIcon(imagePath));
                    }
                } else if (animationState2.isJumping) {
                    if (currentFrameJump <= animationState2.numJumpFrames) {
                        int spriteHeight = animationState2.getSpriteHeightForJump(currentFrameJump);
                        spriteY = animationState2.getSpriteYForPuloFrente2(currentFrameJump);
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\PuloFrente\\" + currentFrameJump + ".png";
                        personagem2.setBounds(posX2[0], spriteY, 186, spriteHeight);
                        personagem2.setIcon(new ImageIcon(imagePath));
                        //System.out.println("Altura do pulo para o frame " + currentFrameJump + ": " + spriteHeight);
                        //System.out.println("spriteY = " + spriteY);
                        currentFrameJump++;
                        if (currentFrameJump > 8) {
                            animationState2.isJumping = false;
                        }
                    } else {
                    	//getSpriteYForPuloFrente1
                        animationState2.isJumping = false; // Sair do estado de pulo
                        currentFrameJump = 1; // Reiniciar contador para o próximo pulo
                        spriteY = 192; // Retornar à posição Y de 192
                    }
                } else if (animationState2.isSocoFraco) {
                    if (currentFrameSocoFraco == 2) {
                    	
                    	if(direcaoB == "Direita") {
                			String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB +"\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                            personagem2.setBounds(posX2[0], spriteY, 258, 260);
                            personagem2.setIcon(new ImageIcon(imagePath));
                		}
                		else {
                			String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB +"\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                            personagem2.setBounds(posX2[0] - 72, spriteY, 258, 260);
                            personagem2.setIcon(new ImageIcon(imagePath));
                		}
                    } else if (currentFrameSocoFraco == 3) {
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem2.setBounds(posX2[0], spriteY, 186, 260);
                        personagem2.setIcon(new ImageIcon(imagePath));
                    } else if (animationState2.isPuloFrente) {
                        currentFramePuloFrente = (currentFramePuloFrente % animationState2.numPuloFrenteFrames) + 1;
                        int spriteHeight = animationState2.getSpriteHeightForJump(currentFramePuloFrente);
                        System.out.println("Sprite Height for Frame " + currentFramePuloFrente + ": " + spriteHeight);
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\PuloFrente\\" + currentFramePuloFrente + ".png";
                        personagem2.setBounds(posX2[0], spriteY, 186, spriteHeight);
                        personagem2.setIcon(new ImageIcon(imagePath));
                    } else {
                        String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\SocoFraco\\" + currentFrameSocoFraco + ".png";
                        personagem2.setBounds(posX2[0], spriteY, 186, 260);
                        personagem2.setIcon(new ImageIcon(imagePath));
                        animationState2.isSocoFraco = false;
                    }
                    currentFrameSocoFraco = (currentFrameSocoFraco % animationState2.numSocoFracoFrames) + 1;
                } else if (animationState2.isChuteFraco) {
                    currentFrameChuteFraco = (currentFrameChuteFraco % animationState2.numChuteFracoFrames) + 1;
                    String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\ChuteFraco\\" + currentFrameChuteFraco + ".png";
                    if (currentFrameChuteFraco == 3) {
                    	if(direcaoB == "Direita") {
                    		personagem2.setBounds(posX2[0], spriteY, 317, 260);
                		}
                		else {
                			personagem2.setBounds(posX2[0] - 131, spriteY, 317, 260);
                		}      
                    } else if (currentFrameChuteFraco == 5) {
                        animationState2.isChuteFraco = false;
                    } else {
                        personagem2.setBounds(posX2[0], spriteY, 186, 260);
                    }
                    personagem2.setIcon(new ImageIcon(imagePath));
                } else {// se ele não estiver fazendo nada, ele cai aqui
                    int coordenadaLocal = personagem2.getX();
                    currentFrameJump = 1;
                    spriteY = 192;
                    personagem2.setBounds(coordenadaLocal, spriteY, 186, 260);// Obrigar o personagem a parar no chão e com a hitbox original (animação base)
                    //System.out.println("X = " + coordenadaLocal + " Y = " + spriteY); // 186x260

                    animationState2.baseFrame = (animationState2.baseFrame % animationState2.numBaseFrames) + 1;
                    String imagePath = "src\\Sprites Street Fighter\\Ken\\"+ direcaoB + "\\Base\\" + animationState2.baseFrame + ".png";
                    personagem2.setIcon(new ImageIcon(imagePath));
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        animationThread1.start();               
        animationThread2.start();

        frame.setVisible(true);
        
        if(posX1[0] > posX2[0]) {
        	direcaoA = "Direita";
        	direcaoA = "Esquerda";
        }else if(posX1[0] < posX2[0]){
        	direcaoA = "Esquerda";
        	direcaoA = "Direita";
        }else {
        	// Mantém
        }

    }
    
    /*
    private static void verificarPosicaoDosPersonagens(AnimationState animationThread1, AnimationState animationThread2) {
        // Obtém as coordenadas X dos personagens
        //int posX1 = animationThread1.getX();
        //int posX2 = animationThread2.getX();

        
        if(pos > posX2[0])
        
        // Verifica qual personagem está mais à esquerda e à direita
        if (posX1 < posX2) {
            System.out.println("Ryu está mais à esquerda e Ken está mais à direita.");
        } else if (posX1 > posX2) {
            System.out.println("Ken está mais à esquerda e Ryu está mais à direita.");
        } else {
            System.out.println("Ryu e Ken estão na mesma posição horizontal.");
        }
    }
    */
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

		public int getSpriteYForPuloFrente(int frame) {
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

		public int getSpriteYForPuloFrente2(int frame) {
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

    private static class AnimationState2 {
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

        private int getSpriteHeightForJump(int frame) {//Tamanho altura do componente do personagem
            switch (frame) {
                case 1:
                    return 260;
                case 2:
                    return 287;
                case 3:
                    return 252;
                case 4:
                    return 217;
                case 5:
                    return 202;
                case 6:
                    return 249;
                case 7:
                    return 298;
                default:
                    return 260; // Altura padrão
            }
        }


        
        private int getSpriteYForPuloFrente1(int frame) {//Posição do componento do personagem durante o pulo
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
        private int getSpriteYForPuloFrente2(int frame) {
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