package com.example.football.service.impl;

import com.example.football.models.dto.playerDtos.PlayerImportDTO;
import com.example.football.models.dto.playerDtos.PlayerImportWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final Path PLAYERS_IMPORT_XML =
            Path.of("src", "main", "resources", "files", "xml", "players.xml");

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, StatRepository statRepository, ModelMapper modelMapper, ValidationUtil validationUtil) throws JAXBException {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

        JAXBContext context = JAXBContext.newInstance(PlayerImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();

//        this.modelMapper.addConverter(new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> context) {
//                return LocalDate.parse(context.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            }
//        });

//        this.modelMapper.addConverter((Converter<String, LocalDate>)
//                context1 -> LocalDate.parse(context1.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

//        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(PLAYERS_IMPORT_XML);
    }

    @Override
    public String importPlayers() throws IOException, JAXBException {
        BufferedReader bufferedReader = Files.newBufferedReader(PLAYERS_IMPORT_XML);
        PlayerImportWrapperDTO playerImportWrapperDTO =
                (PlayerImportWrapperDTO) unmarshaller.unmarshal(bufferedReader);

        return playerImportWrapperDTO.getPlayers().stream()
                .map(this::importPlayer).collect(Collectors.joining("\n"));
    }

    private String importPlayer(PlayerImportDTO playerImportDTO) {
        Set<ConstraintViolation<PlayerImportDTO>> violations = validationUtil.violation(playerImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid player";
        }

        Optional<Player> findPlayerByEmail = this.playerRepository.findByEmail(playerImportDTO.getEmail());

        if (findPlayerByEmail.isPresent()) {
            return "Invalid player";
        }

        Player player = modelMapper.map(playerImportDTO, Player.class);

        Optional<Town> optionalTown = this.townRepository.findByName(playerImportDTO.getTown().getName());
        player.setTown(optionalTown.get());

        Optional<Team> optionalTeam = this.teamRepository.findByName(playerImportDTO.getTeam().getName());
        player.setTeam(optionalTeam.get());

        Optional<Stat> statOptional = this.statRepository.findById(playerImportDTO.getStat().getId());
        player.setStat(statOptional.get());

        this.playerRepository.save(player);
        return "Successfully imported Player " + player.getFirstName() + " " + player.getLastName() + " - " +
                player.getPlayerPosition().toString();
    }

    @Override
    public String exportBestPlayers() {
        LocalDate after = LocalDate.of(1995, 1, 1);
        LocalDate before = LocalDate.of(2003, 1, 1);

        List<Player> players = this.playerRepository
                .findByBirthDateAfterAndBirthDateBeforeOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc
                        (after, before);

        return players.stream().map(Player::toString).collect(Collectors.joining("\n"));
    }
}
